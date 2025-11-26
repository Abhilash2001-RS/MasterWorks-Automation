package com.aurigo.masterworks.testframework.utilities.helper;

import com.aurigo.masterworks.testframework.BaseFramework;
import com.aurigo.masterworks.testframework.utilities.AzureLogger;
import com.aurigo.masterworks.testframework.utilities.enums.AzureResults;
import com.aurigo.masterworks.testframework.utilities.enums.AzureState;
import org.testng.asserts.Assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssertHelper extends BaseFramework {

    private AzureLogger azureLogger;
    private int testRunId;
    private List<AssertionError> softErrors;


    public AssertHelper(AzureLogger azureLogger, int testRunId) {
        this.azureLogger = azureLogger;
        this.testRunId = testRunId;
        softErrors = new ArrayList<>();
    }

    //Asserts with Test case id

    /**
     * Assert true with test case id.
     *
     * @param variable   evaluated expression variable.
     * @param message    Message to be passed.
     * @param testCaseId Test case Ids to update.
     */
    public void assertTrue(boolean variable, String message, String... testCaseId) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertTrue(variable, message);
        } catch (AssertionError e) {
            for (String test : testCaseId) {
                updateFailResult(test, test + ": " + message);
            }
            throw e;
        }
        for (String test : testCaseId) {
            updatePassResult(test, test + ": " + message);
        }
    }

    /**
     * Assert false with test case id.
     *
     * @param variable   evaluated expression variable.
     * @param message    Message to be passed.
     * @param testCaseId Test case Ids to update.
     */
    public void assertFalse(boolean variable, String message, String... testCaseId) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertFalse(variable, message);
        } catch (AssertionError e) {
            for (String test : testCaseId) {
                updateFailResult(test, test + ": " + message);
            }
            throw e;
        }
        for (String test : testCaseId) {
            updatePassResult(test, test + ": " + message);
        }

    }

    /**
     * Assert equals with test case id.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param testCaseId     Test case Id to update.
     * @param <T>            generic data type.
     */
    public <T> void assertEquals(T actual, T expected, String message, String testCaseId) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(testCaseId, testCaseId + ": " + message);
            throw e;
        }
        updatePassResult(testCaseId, testCaseId + ": " + message);
    }

    /**
     * Assert not equals with test case id.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param testCaseId     Test case Id to update.
     * @param <T>            generic data type.
     */
    public <T> void assertNotEquals(T actual, T expected, String message, String testCaseId) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNotEquals(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(testCaseId, testCaseId + ": " + message);
            throw e;
        }
        updatePassResult(testCaseId, testCaseId + ": " + message);
    }

    /**
     * Assert same with test case id.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param testCaseId     Test case Id to update.
     * @param <T>            generic data type.
     */
    public <T> void assertSame(T actual, T expected, String message, String testCaseId) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertSame(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(testCaseId, testCaseId + ": " + message);
            throw e;
        }
        updatePassResult(testCaseId, testCaseId + ": " + message);
    }

    /**
     * Assert not same with test case id.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param testCaseId     Test case Id to update.
     * @param <T>            generic data type.
     */
    public <T> void assertNotSame(T actual, T expected, String message, String testCaseId) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNotSame(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(testCaseId, testCaseId + ": " + message);
            throw e;
        }
        updatePassResult(testCaseId, testCaseId + ": " + message);
    }

    /**
     * Assert null with test case id.
     *
     * @param variable   Evaluated expression variable.
     * @param message    Message to be passed.
     * @param testCaseId Test case Id to update.
     * @param <T>        generic data type.
     */
    public <T> void assertNull(T variable, String message, String testCaseId) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNull(variable, message);
        } catch (AssertionError e) {
            updateFailResult(testCaseId, testCaseId + ": " + message);
            throw e;
        }
        updatePassResult(testCaseId, testCaseId + ": " + message);
    }

    /**
     * Assert not null with test case id.
     *
     * @param variable   Evaluated expression variable.
     * @param message    Message to be passed.
     * @param testCaseId Test case Id to update.
     * @param <T>        generic data type.
     */
    public <T> void assertNotNull(T variable, String message, String testCaseId) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNotNull(variable, message);
        } catch (AssertionError e) {
            updateFailResult(testCaseId, testCaseId + ": " + message);
            throw e;
        }
        updatePassResult(testCaseId, testCaseId + ": " + message);
    }

    /**
     * Assert equals no order with test case id.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param testCaseId     Test case Id to update.
     */
    public void assertEqualsNoOrder(Object[] actual, Object[] expected, String message, String testCaseId) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertEqualsNoOrder(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(testCaseId, testCaseId + ": " + message);
            throw e;
        }
        updatePassResult(testCaseId, testCaseId + ": " + message);
    }

    //Asserts without Test case id

    /**
     * Assert True.
     *
     * @param variable Evaluated expression variable.
     * @param message  Message to be passed.
     */
    public void assertTrue(boolean variable, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertTrue(variable, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            throw e;
        }
        updatePassResult(message);
    }

    /**
     * Assert False.
     *
     * @param variable Evaluated expression variable.
     * @param message  Message to be passed.
     */
    public void assertFalse(boolean variable, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertFalse(variable, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            throw e;
        }
        updatePassResult(message);
    }

    /**
     * Assert equals.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param <T>            generic data type.
     */
    public <T> void assertEquals(T actual, T expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            throw e;
        }
        updatePassResult(message);
    }

    /**
     * Assert not equals.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param <T>            generic data type.
     */
    public <T> void assertNotEquals(T actual, T expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNotEquals(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            throw e;
        }
        updatePassResult(message);
    }

    /**
     * Assert same.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param <T>            generic data type.
     */
    public <T> void assertSame(T actual, T expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertSame(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            throw e;
        }
        updatePassResult(message);
    }

    /**
     * Assert not same.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param <T>            generic data type.
     */
    public <T> void assertNotSame(T actual, T expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNotSame(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            throw e;
        }
        updatePassResult(message);
    }

    /**
     * Assert null.
     *
     * @param variable Evaluated expression variable.
     * @param message  Message to be passed.
     * @param <T>      generic data type.
     */
    public <T> void assertNull(T variable, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNull(variable, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            throw e;
        }
        updatePassResult(message);
    }

    /**
     * Assert not null.
     *
     * @param variable Evaluated expression variable.
     * @param message  Message to be passed.
     * @param <T>      generic data type.
     */
    public <T> void assertNotNull(T variable, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNotNull(variable, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            throw e;
        }
        updatePassResult(message);
    }

    /**
     * Assert without order.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     */
    public void assertEqualsNoOrder(Object[] actual, Object[] expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertEqualsNoOrder(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            throw e;
        }
        updatePassResult(message);
    }

    //Soft Asserts

    /**
     * Soft assert true.
     *
     * @param variable Evaluated expression variable.
     * @param message  Message to be passed.
     */
    public void softAssertTrue(boolean variable, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertTrue(variable, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            softErrors.add(e);
        }
        updatePassResult(message);
    }

    /**
     * Soft assert false.
     *
     * @param variable Evaluated expression variable.
     * @param message  Message to be passed.
     */
    public void softAssertFalse(boolean variable, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertFalse(variable, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            softErrors.add(e);
        }
        updatePassResult(message);
    }

    /**
     * Soft assert equals.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param <T>            generic data type.
     */
    public <T> void softAssertEquals(T actual, T expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            softErrors.add(e);
        }
        updatePassResult(message);
    }

    /**
     * Soft assert not equals.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param <T>            generic data type.
     */
    public <T> void softAssertNotEquals(T actual, T expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNotEquals(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            softErrors.add(e);
        }
        updatePassResult(message);
    }

    /**
     * Soft assert same.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param <T>            generic data type.
     */
    public <T> void softAssertSame(T actual, T expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertSame(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            softErrors.add(e);
        }
        updatePassResult(message);
    }

    /**
     * Soft assert not same.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param <T>            generic data type.
     */
    public <T> void softAssertNotSame(T actual, T expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNotSame(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            softErrors.add(e);
        }
        updatePassResult(message);
    }

    /**
     * Soft assert null.
     *
     * @param variable Evaluated expression variable.
     * @param message  Message to be passed.
     * @param <T>      generic data type.
     */
    public <T> void softAssertNull(T variable, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNull(variable, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            softErrors.add(e);
        }
        updatePassResult(message);
    }

    /**
     * Soft assert not null.
     *
     * @param variable Evaluated expression variable.
     * @param message  Message to be passed.
     * @param <T>      generic data type.
     */
    public <T> void softAssertNotNull(T variable, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertNotNull(variable, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            softErrors.add(e);
        }
        updatePassResult(message);
    }

    /**
     * Soft assert without order.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     */
    public void softAssertEqualsNoOrder(Object[] actual, Object[] expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertEqualsNoOrder(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            softErrors.add(e);
        }
        updatePassResult(message);
    }

    /**
     * Soft asserts all.
     *
     * @param testCaseIds String array of test case Id's
     */
    public void softAssertAll(String... testCaseIds) {
        if (!this.softErrors.isEmpty()) {
            updateFailResultsMultiple(testCaseIds);
            String errorMessages = Arrays.toString(softErrors.toArray());
            softErrors.clear();
            throw new AssertionError("Soft Assert All Failed Errors Occured are " + errorMessages);
        }
        updatePassResultsMultiple(testCaseIds);
    }


    /**
     * Update and log pass result.
     *
     * @param testCaseId Test case Id to be updated.
     * @param message    Message to be logged.
     */
    private void updatePassResult(String testCaseId, String message) {
        if (testRunId > 0) {
            azureLogger.updateTestResult(testRunId, new String[]{testCaseId}, AzureState.Completed, AzureResults.Passed, "Assert Pass.");
        }
        updatePassResult(message);
    }

    /**
     * Update and log fail result.
     *
     * @param testCaseId Test case Id to be updated.
     * @param message    Message to be logged.
     */
    private void updateFailResult(String testCaseId, String message) {
        if (testRunId > 0) {
            azureLogger.updateTestResult(testRunId, new String[]{testCaseId}, AzureState.Completed, AzureResults.Failed, "Assert Failure.");
        }
        updateFailResult(message);
    }

    /**
     * Log Pass result.
     *
     * @param message Message to be logged.
     */
    private void updatePassResult(String message) {
        var successMessage = "<div style=\"color:green;\">" + message + " : <b>Success</b></div></div>";
        logger().pass(successMessage);
    }

    /**
     * Log Fail result.
     *
     * @param message Message to be logged.
     */
    private void updateFailResult(String message) {
        var failureMessage = "<div style=\"color:red;\">" + message + " : <b>Failure</b></div></div>";
        logger().fail(failureMessage);
    }

    /**
     * Update multiple test results as pass in azure.
     *
     * @param testCaseIds String array of test case Id's.
     */
    private void updatePassResultsMultiple(String[] testCaseIds) {
        if (testRunId > 0) {
            azureLogger.updateTestResult(testRunId, testCaseIds, AzureState.Completed, AzureResults.Passed, "Assert Pass");
        }
    }

    /**
     * Update multiple test results as fail in azure.
     *
     * @param testCaseIds String array of test case Id's.
     */
    private void updateFailResultsMultiple(String[] testCaseIds) {
        if (testRunId > 0) {
            azureLogger.updateTestResult(testRunId, testCaseIds, AzureState.Completed, AzureResults.Failed, "Assert Failure.");
        }
    }
}
