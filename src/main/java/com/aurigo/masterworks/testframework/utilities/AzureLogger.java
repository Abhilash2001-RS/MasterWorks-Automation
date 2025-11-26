package com.aurigo.masterworks.testframework.utilities;

import com.aurigo.masterworks.testframework.utilities.enums.AzureResults;
import com.aurigo.masterworks.testframework.utilities.enums.AzureState;
import com.aurigo.masterworks.testframework.utilities.helper.EnvironmentHelper;
import com.aurigo.masterworks.testframework.utilities.models.*;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.util.Strings;

import java.io.File;
import java.io.FileInputStream;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class AzureLogger {
    private static final Logger logger = LogManager.getLogger(AzureLogger.class);
    private static HashMap<String, String> testPointMap = new HashMap<>();
    private static HashMap<String, List<String>> allTestsMap = new HashMap<>();
    private static TestRunRoot testRunRoot;
    private final String commentMarkDownFormat = "###[TC %s - %s](%s)\n ### %s";
    private String azureUrl;
    private String planId;
    private String suiteId;
    private String automationUserEmail;
    private String automationUserPassKey;
    private RestAssuredUtil restAssuredUtil;
    private Set<SuiteTree> treeHashSet = new HashSet<>();
    private static List<TestPoint> testPoints =new ArrayList();

    public AzureLogger(String projectName) {
        String azureDevOpsUrl = EnvironmentHelper.getPropertyValue("azureDevOps.url");
        logger.info(String.format("Project Name:%s", projectName));
        azureUrl = azureDevOpsUrl + projectName;
        planId = System.getProperty("azureDevOps.planId");
        planId = Strings.isNullOrEmpty(planId)?EnvironmentHelper.getPropertyValue("azureDevOps.planId"):planId;
        suiteId = System.getProperty("azureDevOps.suiteId");
        suiteId = Strings.isNullOrEmpty(suiteId)?EnvironmentHelper.getPropertyValue("azureDevOps.suiteId"):suiteId;
        automationUserEmail = EnvironmentHelper.getPropertyValue("azureDevOps.automationUserEmail");
        automationUserPassKey = EnvironmentHelper.getPropertyValue("azureDevOps.automationUserPassKey");
        restAssuredUtil = new RestAssuredUtil();
    }

    /**
     * Create a new Test Run in VSTS and returns the runId
     *
     * @param testCaseIds -   Test case IDs.
     * @param runTitle    -   Test run title.
     * @return -   Test run ID.
     */
    public int createTestRun(HashSet<String> testCaseIds, String runTitle) {
        Plan plan = new Plan();
        plan.id = planId;
        CreateRunRequest request = new CreateRunRequest();
        if(testPoints.size()<1) {
            testPoints = getAllTestPoints();
        }
        List<Integer> pointsList = new ArrayList<>();

        testPoints.stream()
                .filter(point -> (testCaseIds.stream()
                        .filter(e -> point.getTestCase().getId().equals(e))
                        .count()) > 0).forEach(p -> {
            pointsList.add(Integer.parseInt(p.getId()));
            addPointToMap(p.getTestCase().getId(), p.getId());
            testPointMap.put(p.getTestCase().getId(), p.getId());
        });
        var missingTests = testCaseIds.stream().filter(x->!testPointMap.entrySet().stream().anyMatch(y->y.getKey().equals(x))).collect(Collectors.toList());
        logger.info("-------------------------- Missing tests from the Azure DevOps Test Run --------------------------");
        missingTests.forEach(logger::info);
        logger.info("----------------------------------------------------------------------------");
        logger.info("-------------------------- Test Case to Point Map --------------------------");
        allTestsMap.entrySet().forEach(x->logger.info(String.format("TestCaseId:%s Available TestPoints:%s",x.getKey(),String.join(",",x.getValue()))));
        logger.info("----------------------------------------------------------------------------");
        request.name = runTitle;
        request.plan = plan;
        request.automated = true;
        request.pointIds = pointsList;
        var buildNumber = System.getProperty("buildId");
        if (Strings.isNotNullAndNotEmpty(buildNumber)) {
            var associatedBuild = new Build();
            associatedBuild.id = buildNumber;
            request.build = associatedBuild;
        }
        Gson gson = new Gson();
        String requestJson = gson.toJson(request);
        String endPoint = azureUrl + "/_apis/test/runs?api-version=5.0";
        logger.info("------------------Create Test Run------------------");
        Response response = restAssuredUtil.doPostRequest(endPoint, automationUserEmail, automationUserPassKey, requestJson);
        logger.info(String.format("Response:%s", response.asString()));
        logger.info("---------------------------------------------------");
        Integer testRunId = response.jsonPath().get("id");
        testRunRoot = getTestRun(testRunId);
        return testRunId;
    }

    /**
     * Method to find all the test case id to test point relationship
     *
     * @param testCaseId - Test Case Id
     * @param testPointId - Test Point Id
     */
    private void addPointToMap(String testCaseId, String testPointId){
        if(allTestsMap.containsKey(testCaseId)){
            var existingValues=allTestsMap.get(testCaseId);
            existingValues.add(testPointId);
            allTestsMap.put(testCaseId,existingValues);
        }else{
            List<String> newValues=new ArrayList<>();
            newValues.add(testPointId);
            allTestsMap.put(testCaseId,newValues);
        }
    }

    /**
     * Method to get all the test points
     *
     * @return List of Test Points
     */
    private List<TestPoint> getAllTestPoints() {
        treeHashSet=new HashSet<>();
        String suitesInPlanEndPointTree = azureUrl + "/_apis/testplan/Plans/" + planId + "/suites?asTreeView=True&api-version=6.1-preview.1";
        var response = restAssuredUtil.doGetRequest(suitesInPlanEndPointTree, automationUserEmail, automationUserPassKey);
        var suitesInPlanTreeRoot = response.jsonPath().getObject("$", SuitesInPlanTree.class);

        traverseSuites(suitesInPlanTreeRoot.getValue());
        var originalSet = treeHashSet;
        var suiteTreeList=originalSet.stream().filter(x->x.getParentSuite()!=null && x.getParentSuite().getId()==Integer.parseInt(suiteId)).distinct().collect(Collectors.toList());
        treeHashSet=new HashSet<>();
        traverseSuites(suiteTreeList);
        treeHashSet.add(originalSet.stream().filter(x->x.getId()==Integer.parseInt(suiteId)).findFirst().get());
        List<TestPoint> finalPointList=new ArrayList<>();
        for(var suite : treeHashSet){
            String endPoint = azureUrl + "/_apis/test/Plans/" + planId + "/Suites/" + suite.getId() + "/points?api-version=5.0";
            response = restAssuredUtil.doGetRequest(endPoint, automationUserEmail, automationUserPassKey);
            if(response==null){
                return new ArrayList<>();
            }
            var testPointRoot = response.jsonPath().getObject("$", TestPointRoot.class);
            finalPointList.addAll(testPointRoot.getValue());
        }
        return finalPointList;
    }

    /**
     * Method to fetch all test case ids from a suite
     *
     * @return list of test case ids
     */
    public List<String> getTestCaseIdsFromSuite(){
        if(testPoints.size()<1) {
            testPoints = getAllTestPoints();
        }
        List<String> testCaseIds=new ArrayList<>();
        testPoints.stream().forEach(x->testCaseIds.add(x.getTestCase().getId()));
         return testCaseIds;
    }


    /**
     * Traverse through all test suites
     *
     * @param suiteTrees - suite list
     */
    private void traverseSuites(List<SuiteTree> suiteTrees) {
        SuiteTree result = null;
        for(SuiteTree l : suiteTrees) {
            result = traverseNodes(new HashSet(), l );
            if(result != null)
                break;
        }
    }

    /**
     * Traversing through all the child nodes of the tree
     *
     * @param visitedAlready - Already traversed node
     * @param current - current node
     * @return list of all suites in the node
     */
    private SuiteTree traverseNodes(Set<SuiteTree> visitedAlready, SuiteTree current) {
        if (visitedAlready.contains(current))
            return current;
        visitedAlready.add(current);
        treeHashSet.addAll(visitedAlready);
        SuiteTree result = null;
        if (current.getHasChildren()) {
            for (SuiteTree l : current.getChildren()) {
                result = traverseNodes(visitedAlready, l);
                if (result != null)
                    break;
            }
        }
        return result;
    }

    /**
     * Create Attachment.
     *
     * @param testRunId - Test run ID.
     * @param filePath  Creates an attachment in the test run with the filepath passed
     */
    public void createAttachment(int testRunId, String filePath) {
        String encodedBase64 = null;
        File originalFile;
        try {
            originalFile = new File(filePath);
            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
            byte[] bytes = new byte[(int) originalFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
            var endPoint = azureUrl + "/_apis/test/Runs/" + testRunId + "/attachments?api-version=5.0-preview.1";
            var request = new CreateAttchmentRequest();
            request.attachmentType = "GeneralAttachment";
            request.fileName = originalFile.getName();
            request.stream = encodedBase64;
            Gson gson = new Gson();
            var requestJson = gson.toJson(request);
            restAssuredUtil.doPostRequest(endPoint, automationUserEmail, automationUserPassKey, requestJson);
        } catch (Exception e) {
            logger.error("Error occured while creating attached" + e.getMessage());
        }
    }

    /**
     * Updates the Test Run status to Completed
     *
     * @param testRunId - Test Run ID
     * @param awsUrl    - Url of the archived log
     */
    public void updateTestRun(int testRunId, String awsUrl)
    {
        var endPoint = azureUrl + "/_apis/test/Runs/" + testRunId + "?api-version=5.0";
        var updateRunRequest = new UpdateRunRequest();
        var completedDate = Instant.now().toString();
        updateRunRequest.comment = Strings.isNotNullAndNotEmpty(awsUrl) ? String.format("Archived Report: %s", awsUrl)
                : "Automation Run completed @" + completedDate;
        updateRunRequest.state = "Completed";
        updateRunRequest.completedDate = completedDate;
        Gson gson = new Gson();
        var requestJson = gson.toJson(updateRunRequest);
        logger.info("------------------Update Test Run------------------");
        Response response = restAssuredUtil.doPatchRequest(endPoint, automationUserEmail, automationUserPassKey, requestJson);
        logger.info(String.format("Response:%s", response.asString()));
        logger.info("---------------------------------------------------");
    }

    /**
     * Gets the test run results
     *
     * @param testRunId - test run id
     * @return test run results object
     */
    public TestRunRoot getTestRun(int testRunId) {
        String maxResultsCountToFetch = EnvironmentHelper.getPropertyValue("maxResultsCountToFetch");
        var endPoint = azureUrl + "/_apis/test/Runs/" + testRunId + "/results?$top=" + (Strings.isNullOrEmpty(maxResultsCountToFetch) ? "3000" : maxResultsCountToFetch) + "&api-version=5.0";
        //Fallback is set to 3000 in-case the config value is not set
        var test = restAssuredUtil.doGetRequest(endPoint, automationUserEmail, automationUserPassKey);
        return test.jsonPath().getObject("$", TestRunRoot.class);
    }

    /**
     * Method to get the url for the test run created
     *
     * @param testRunId Id of the test run
     * @return test run url
     */
    public String getTestRunUrl(int testRunId) {
        return azureUrl + String.format("/_testManagement/runs?runId=%d&_a=runCharts", testRunId);
    }

    /**
     * Get list of test results in pending state
     *
     * @param testRunId   Test run ID
     * @param testCaseIds List of test cases
     * @return list of test results in pending state
     */
    public List<TestResult> getPendingTests(int testRunId, String[] testCaseIds) {
        List<TestResult> pendingTests = new ArrayList<>();
        try {
            testRunRoot = getTestRun(testRunId);

            for (String testCaseId : testCaseIds) {
                var testPointId = testPointMap.get(testCaseId);
                if (testPointId != null) {
                    var testResult = testRunRoot.getValue().stream().filter(v -> v.getTestPoint().getId().equals(testPointId)).findFirst().get();
                    if (testResult.getState().equals(AzureState.InProgress.name())) {
                        pendingTests.add(testResult);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("Exception occured while fetching Pending tests:" + ex.getMessage());
        }
        return pendingTests;
    }

    /**
     * Update Test Result based on the parameters passed
     *
     * @param testRunId   -   Test Run ID.
     * @param testCaseIds -   List of Test Case ID's.
     * @param outcome     -   Outcome for the test case.
     * @param comment     -   Log exception/failure as Comment.
     */
    public void updateTestResult(int testRunId, String[] testCaseIds, AzureState state, AzureResults outcome, String comment) {
        var listOfRequest = new ArrayList<UpdateResultRequest>();
        testRunRoot = getTestRun(testRunId);

        try {
            for (String testCaseId : testCaseIds) {
                var testPointId = testPointMap.get(testCaseId);
                // Update the test result, only in case of state is pending. Else the test state have already been updated.
                if (testPointId != null) {
                    var testResult = testRunRoot.getValue().stream().filter(v -> v.getTestPoint().getId().equals(testPointId)).findFirst().get();
                    if (Strings.isNullOrEmpty(testResult.getOutcome())) {
                        var updateTestResult = new UpdateResultRequest();
                        updateTestResult.state = state.name();
                        updateTestResult.id = testResult.getId();
                        if (!state.equals(AzureState.InProgress)) {
                            if (outcome.equals(AzureResults.Failed) || outcome.equals(AzureResults.Blocked)) {
                                updateTestResult.comment = String.format(commentMarkDownFormat, testCaseId, testResult.getTestCase().getName(), getWorkItemUrl(testCaseId), comment);
                            }
                        }
                        if (state.equals(AzureState.InProgress)) {
                            updateTestResult.startedDate = Instant.now().toString();
                        } else {
                            updateTestResult.startedDate = testResult.getStartedDate();
                        }
                        updateTestResult.outcome = outcome.name();
                        updateTestResult.completedDate = Instant.now().toString();
                        listOfRequest.add(updateTestResult);
                    } else if (!testResult.getOutcome().equalsIgnoreCase("Passed")) {
                        var updateTestResult = new UpdateResultRequest();
                        updateTestResult.id = testResult.getId();
                        if (outcome.equals(AzureResults.Blocked) && testResult.getOutcome().equalsIgnoreCase(AzureResults.Failed.name())) {
                            updateTestResult.outcome = testResult.getOutcome();
                            updateTestResult.comment = testResult.getComment();
                        } else {
                            updateTestResult.outcome = outcome.name();
                            updateTestResult.comment = String.format(commentMarkDownFormat, testCaseId, testResult.getTestCase().getName(), getWorkItemUrl(testCaseId), comment);
                        }
                        if (state.equals(AzureState.InProgress)) {
                            updateTestResult.startedDate = Instant.now().toString();
                            updateTestResult.outcome = outcome.name();
                        } else {
                            updateTestResult.startedDate = testResult.getStartedDate();
                        }
                        updateTestResult.completedDate = Instant.now().toString();
                        updateTestResult.state = state.name();
                        listOfRequest.add(updateTestResult);
                    }
                }
            }
            if (listOfRequest.size() > 0) {
                Gson gson = new Gson();
                var requestJson = gson.toJson(listOfRequest);
                var endPoint = azureUrl + "/_apis/test/Runs/" + testRunId + "/results?api-version=5.0";
                Response response = restAssuredUtil.doPatchRequest(endPoint, automationUserEmail, automationUserPassKey, requestJson);
                logger.info(String.format("Response:%s", response.asString()));
            }
        } catch (Exception e) {
            //Removed ExceptionHandler to avoid logging this failures in extent report, console log is enough for this
            logger.info(String.format("Exception occurred while updating the result :%s", e.getMessage()));
        }
    }

    /**
     * Update Test Result based on the parameters passed
     *
     * @param testRunId              -   Test Run ID.
     * @param testCaseIds            -   List of Test Case ID's.
     * @param outcome                -   Outcome for the test case.
     * @param comment                -   Log exception/failure as Comment.
     * @param screenshotBase64String - base64 string of the screenshot
     */
    public void updateTestResultWithScreenshot(int testRunId, String[] testCaseIds, AzureState state, AzureResults outcome, String comment, String screenshotBase64String) {
        if (Strings.isNullOrEmpty(screenshotBase64String)) {
            updateTestResult(testRunId, testCaseIds, state, outcome, comment);
        } else {
            var listOfRequest = new ArrayList<UpdateResultRequest>();
            testRunRoot = getTestRun(testRunId);
            try {
                for (String testCaseId : testCaseIds) {
                    var testPointId = testPointMap.get(testCaseId);
                    // Update the test result, only in case of state is pending. Else the test state have already been updated.
                    if (testPointId != null) {
                        var testResult = testRunRoot.getValue().stream().filter(v -> v.getTestPoint().getId().equals(testPointId)).findFirst().get();
                        if (Strings.isNullOrEmpty(testResult.getOutcome())) {
                            var updateTestResult = new UpdateResultRequest();
                            updateTestResult.state = state.name();
                            updateTestResult.id = testResult.getId();
                            if (!state.equals(AzureState.InProgress)) {
                                if (outcome.equals(AzureResults.Failed) || outcome.equals(AzureResults.Blocked)) {
                                    updateTestResult.comment = String.format(commentMarkDownFormat, testCaseId, testResult.getTestCase().getName(), getWorkItemUrl(testCaseId), comment);
                                    attachScreenshotsToTestResult(testRunId, testResult.getId(), screenshotBase64String);
                                }
                            }
                            if (state.equals(AzureState.InProgress)) {
                                updateTestResult.startedDate = Instant.now().toString();
                            } else {
                                updateTestResult.startedDate = testResult.getStartedDate();
                            }
                            updateTestResult.outcome = outcome.name();
                            updateTestResult.completedDate = Instant.now().toString();
                            listOfRequest.add(updateTestResult);
                        } else if (!testResult.getOutcome().equalsIgnoreCase("Passed")) {
                            var updateTestResult = new UpdateResultRequest();
                            updateTestResult.id = testResult.getId();
                            if (outcome.equals(AzureResults.Blocked) && testResult.getOutcome().equalsIgnoreCase(AzureResults.Failed.name())) {
                                updateTestResult.outcome = testResult.getOutcome();
                                updateTestResult.comment = testResult.getComment();
                                attachScreenshotsToTestResult(testRunId, testResult.getId(), screenshotBase64String);
                            } else {
                                updateTestResult.outcome = outcome.name();
                                updateTestResult.comment = String.format(commentMarkDownFormat, testCaseId, testResult.getTestCase().getName(), getWorkItemUrl(testCaseId), comment);
                                attachScreenshotsToTestResult(testRunId, testResult.getId(), screenshotBase64String);
                            }
                            if (state.equals(AzureState.InProgress)) {
                                updateTestResult.startedDate = Instant.now().toString();
                                updateTestResult.outcome = outcome.name();
                            } else {
                                updateTestResult.startedDate = testResult.getStartedDate();
                            }
                            updateTestResult.completedDate = Instant.now().toString();
                            updateTestResult.state = state.name();
                            listOfRequest.add(updateTestResult);
                        }
                    }
                }
                if (listOfRequest.size() > 0) {
                    Gson gson = new Gson();
                    var requestJson = gson.toJson(listOfRequest);
                    var endPoint = azureUrl + "/_apis/test/Runs/" + testRunId + "/results?api-version=5.0";
                    Response response = restAssuredUtil.doPatchRequest(endPoint, automationUserEmail, automationUserPassKey, requestJson);
                    logger.info(String.format("Response:%s", response.asString()));


                }
            } catch (Exception e) {
                //Removed ExceptionHandler to avoid logging this failures in extent report, console log is enough for this
                logger.info(String.format("Exception occurred while updating the result :%s", e.getMessage()));
            }
        }
    }

    /**
     * Method to attach screenshots to a test result
     *
     * @param testRunId    - test run id
     * @param testResultId - test result id
     * @param base64String - base64 stream string of the screenshot to be attached
     */
    private void attachScreenshotsToTestResult(int testRunId, int testResultId, String base64String) {
        var endPoint = azureUrl + "/_apis/test/Runs/" + testRunId + "/Results/" + testResultId + "/attachments?api-version=5.0-preview.1";
        var request = new CreateAttchmentRequest();
        request.attachmentType = "GeneralAttachment";
        request.fileName = String.format("%s_screenshot.gif", testResultId);
        request.stream = base64String;
        Gson gson = new Gson();
        var requestJson = gson.toJson(request);
        restAssuredUtil.doPostRequest(endPoint, automationUserEmail, automationUserPassKey, requestJson);
    }

    /**
     * Get Test run statistics
     *
     * @param testRunId -   Test run id.
     * @return -   Test run statistics.
     */
    public RunStatistics getRunStatistics(int testRunId) {
        var endPoint = azureUrl + "/_apis/test/Runs/" + testRunId + "?api-version=5.0";
        var response = restAssuredUtil.doGetRequest(endPoint, automationUserEmail, automationUserPassKey);

        return response.jsonPath().getObject("$", RunStatistics.class);
    }

    /**
     * Get Work Item Url
     *
     * @param workItemId -  WorkItem ID.
     * @return -   WorkItem URL
     */
    public String getWorkItemUrl(String workItemId) {
        return String.format("%s/_workitems/edit/%s/", azureUrl, workItemId);
    }
}
