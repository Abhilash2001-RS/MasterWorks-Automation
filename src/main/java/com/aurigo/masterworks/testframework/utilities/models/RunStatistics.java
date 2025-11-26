package com.aurigo.masterworks.testframework.utilities.models;

import com.aurigo.masterworks.testframework.utilities.enums.AzureResults;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RunStatistics {

    private int totalTests;
    private int incompleteTests;
    private int notApplicableTests;
    private int passedTests;
    private int unanalyzedTests;

    private List<Result> runStatistics;

    /**
     * Gets the run statistics
     *
     * @return test run statistics
     */
    public List<Result> getRunStatistics() {
        return runStatistics;
    }

    /**
     * Gets the count of total tests executed
     *
     * @return count of all tests executed
     */
    public int getTotalTests() {
        return totalTests;
    }

    /**
     * Gets the count of passed tests
     *
     * @return count of passed tests
     */
    public int getPassedTests() {
        return passedTests;
    }

    /**
     * Gets the count of failed tests
     *
     * @return count of failed tests
     */
    public int getFailedTestsCount() {
        var statistics = getRunStatistics();
        if (statistics.stream().anyMatch(x -> x.outcome.equals(AzureResults.Failed.name()))) {
            return statistics.stream().filter(x -> x.outcome.equals(AzureResults.Failed.name())).findFirst().get().count;
        } else {
            return 0;
        }
    }

    /**
     * Gets the count of skipped/blocked tests
     *
     * @return count of skipped/blocked tests
     */
    public int getBlockedTestsCount() {
        var statistics = getRunStatistics();
        int blockedTestsCount = 0;
        if (statistics.stream().anyMatch(x -> x.outcome.equals(AzureResults.Blocked.name()))) {
            blockedTestsCount += statistics.stream().filter(x -> x.outcome.equals(AzureResults.Blocked.name())).findFirst().get().count;
        }
        if (statistics.stream().anyMatch(x -> x.outcome.equals(AzureResults.None.name()))) {
            blockedTestsCount += statistics.stream().filter(x -> x.outcome.equals(AzureResults.None.name())).findFirst().get().count;
        }
        return blockedTestsCount;
    }

    /**
     * Gets the count of incomplete tests
     *
     * @return count of all incomplete tests
     */
    public int getIncompleteTests() {
        return incompleteTests;
    }
}

