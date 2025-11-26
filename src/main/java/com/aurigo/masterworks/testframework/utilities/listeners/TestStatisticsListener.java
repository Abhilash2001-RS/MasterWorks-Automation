package com.aurigo.masterworks.testframework.utilities.listeners;

import com.aurigo.masterworks.testframework.utilities.annotations.TestInfo;
import com.aurigo.masterworks.testframework.utilities.models.SuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestStatisticsListener implements ITestListener {
    public static Map<ITestNGMethod, String> allTests = new HashMap<>();
    public static Map<String, Boolean> allTestResults = new HashMap<>();
    public static Map<ITestNGMethod, String> passedTests = new HashMap<>();
    public static Map<ITestNGMethod, String> failedTests = new HashMap<>();
    public static Map<ITestNGMethod, String> skippedTests = new HashMap<>();
    private static Map<String, HashMap<String, String>> suiteMap = new HashMap<>();

    /**
     * Method to get the suite wise results when azure logging in OFF
     *
     * @return suite wise result
     */
    public static List<SuiteResult> getSuiteWiseResults() {
        List<String> suiteList = new ArrayList(TestStatisticsListener.allTests.values());
        var distinctList = suiteList.stream().distinct().collect(Collectors.toList());
        List<SuiteResult> suiteResults = new ArrayList<>();
        for (var item : distinctList) {
            var result = new SuiteResult();
            result.suiteName = item;
            result.allTestCount = allTests.entrySet()
                    .stream()
                    .filter(map -> item.equals(map.getValue())).count();
            result.passedCount = passedTests.entrySet()
                    .stream()
                    .filter(map -> item.equals(map.getValue())).count();
            result.failedCount = failedTests.entrySet()
                    .stream()
                    .filter(map -> item.equals(map.getValue())).count();
            result.skipCount = skippedTests.entrySet()
                    .stream()
                    .filter(map -> item.equals(map.getValue())).count();
            result.passPercentage = ((float) result.passedCount / (float) result.allTestCount) * 100.0;
            suiteResults.add(result);

        }
        return suiteResults;
    }

    @Override
    public void onTestStart(ITestResult result) {
        var suiteName = result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Test.class).suiteName();
        var testCaseIds = result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(TestInfo.class).testIds();
        if (suiteMap.containsKey(suiteName)) {
            var existingIds = suiteMap.get(suiteName);
            Arrays.stream(testCaseIds).forEach(x -> existingIds.put(x, ""));
            suiteMap.put(suiteName, existingIds);
        } else {
            HashMap<String, String> idsToAdd = new HashMap<>();
            Arrays.stream(testCaseIds).forEach(x -> idsToAdd.put(x, ""));
            suiteMap.put(suiteName, idsToAdd);
        }
        allTests.put(result.getMethod(), result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Test.class).suiteName());
        allTestResults.put(result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Test.class).testName(),false);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        allTestResults.put(result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Test.class).testName(),true);
        passedTests.put(result.getMethod(), result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Test.class).suiteName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        allTestResults.put(result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Test.class).testName(),false);
        failedTests.put(result.getMethod(), result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Test.class).suiteName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        allTestResults.put(result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Test.class).testName(),false);
        skippedTests.put(result.getMethod(), result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Test.class).suiteName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
