package com.aurigo.masterworks.testframework.utilities.listeners;

import com.aurigo.masterworks.testframework.BaseFramework;
import com.aurigo.masterworks.testframework.utilities.helper.EnvironmentHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest extends BaseFramework implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = Integer.parseInt(EnvironmentHelper.getPropertyValue("failTestRetryCount"));
    private static final Logger logger = LogManager.getLogger(RetryTest.class);

    /**
     * Retry the test in case if test has failed.
     *
     * @param result -   Test result object.
     * @return -   Boolean to run the test.
     */
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            logger.info("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + ". Attempt No." + (retryCount + 1));
            retryCount++;
            return true;
        }
        return false;
    }

    /**
     * Get test result status.
     *
     * @param status -   Test result status.
     * @return -   Status in string.
     */
    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }
}
