package com.aurigo.masterworks.testframework.utilities.helper;


import com.aurigo.masterworks.testframework.BaseFramework;
import com.aurigo.masterworks.testframework.utilities.RestAssuredUtil;

import java.util.ArrayList;
import java.util.List;

public class CodeCoverageHelper extends BaseFramework {

    private final static String ccAgentBaseUrl=EnvironmentHelper.getPropertyValue("ccagent.url");
    private static final RestAssuredUtil restAssuredUtil= new RestAssuredUtil();

    /**
     * Method to start capturing code coverage
     *
     * @param buildUrl - url of the build
     */
    public static void startCoverage(String buildUrl){
        WaitHelper.waitForUrlToBeAvailable(ccAgentBaseUrl,180);
        String endPoint = ccAgentBaseUrl + "api/coverage?action=start";
        try{
            var response = restAssuredUtil.doPostRequest(endPoint);
        }catch(Exception ex){
            logger().info("Exception occurred while starting coverage");
        }
        logger().info("Starting code coverage session");
        WaitHelper.waitForUrlToBeAvailable(buildUrl,180);
    }

    /**
     * Method to stop capturing code coverage ad generate the xml report
     *
     * @param buildUrl - url of the build
     */
    public static void stopCoverage(String buildUrl){
        WaitHelper.waitForUrlToBeAvailable(buildUrl,180);
        String endPoint = ccAgentBaseUrl + "api/coverage?action=stop";
        logger().info("Stopping code coverage session");
        try{
            var response = restAssuredUtil.doPostRequest(endPoint);
        }catch(Exception ex){
            logger().info("Exception occurred while stopping coverage");
        }
        WaitHelper.waitForUrlToBeAvailable(buildUrl,180);
    }

    /**
     * Method to create the Test to Class mapping from the generated xml report
     *
     *
     * @param methodName -  Test method name for which the mapping is done
     */
    public static void createTestToClassMap(String methodName){
        logger().info("Creating test to class mapping");
        WaitHelper.waitForUrlToBeAvailable(ccAgentBaseUrl,180);
        String endPoint = ccAgentBaseUrl + "api/coverage/maptesttoclass?test=" + methodName;
        try{
            var response = restAssuredUtil.doPostRequest(endPoint);
        }catch(Exception ex){
            logger().info("Exception occurred while stopping coverage");
        }
    }

    /**
     * Get impacted test list based on the build ID
     *
     * @param program - Program name
     * @param buildId - Build ID to extract the test list
     * @param runType - test run type to filter tests
     * @return list of impacted tests
     */
    public static List<String> getImpactedTests(String program, String buildId, String runType){
        String endPoint = ccAgentBaseUrl + "api/tests/impacted?program=" + program+"&buildId="+buildId;
        try{
            var response = restAssuredUtil.doGetRequest(endPoint,"","");
            return response.jsonPath().getList("$",String.class);
        }catch(Exception ex){
            logger().info("Exception occurred while fetching impacted tests");
            return new ArrayList<>();
        }
    }
}
