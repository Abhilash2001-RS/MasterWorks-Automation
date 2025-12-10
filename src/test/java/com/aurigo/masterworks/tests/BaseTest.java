package com.aurigo.masterworks.tests;

import com.aurigo.masterworks.testframework.BaseFramework;
import com.aurigo.masterworks.testframework.utilities.*;
import com.aurigo.masterworks.testframework.utilities.annotations.TestInfo;
import com.aurigo.masterworks.testframework.utilities.enums.AzureResults;
import com.aurigo.masterworks.testframework.utilities.enums.AzureState;
import com.aurigo.masterworks.testframework.utilities.helper.*;
import com.aurigo.masterworks.testframework.utilities.models.ContextualHelp;
import com.aurigo.masterworks.testframework.utilities.models.environment.Build;
import com.aurigo.masterworks.testframework.webUI.BasePage;
import com.aurigo.masterworks.testframework.webUI.common.LoginPage;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.BrowserType;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import j2html.TagCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.util.Strings;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static com.aurigo.masterworks.testframework.utilities.enums.AllurePathHelper.CURRENT_ENV;
import static com.aurigo.masterworks.testframework.utilities.enums.AllurePathHelper.CURRENT_RUN_TYPE;
import static j2html.TagCreator.*;
import static j2html.TagCreator.each;

public class BaseTest extends BaseFramework {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    public static AssertHelper assertHelper;
    public static HashSet<String> testCaseIds = new HashSet<>();
    private static AzureLogger azureLogger;
    private static int testRunId = 0;
    private static String buildId;
    private static boolean isAzureLoggingEnabled;
    private WebDriver driver;
    private static List<String> testCaseIdsFromSuite;
    private static boolean isEmailReportingEnabled;
    private static boolean isUploadToS3Required;
    private static boolean isAPITest = false;
    private static boolean isMobileTest = false;
    private static boolean noGroupAndModuleFilterInForce = false;
    private static boolean requiresWebDriver = true;
    private static String chartFilePath;
    private static String runChartCid;
    private static String executionTimeStamp;
    private static String buildVersion;
    private WebDriverUtil webDriverUtil;
    private static HashMap<String, HashMap<String, String>> suiteResultSet;
    public Build currentBuild;
    protected String dateFormat;
    protected String strProjectID = "ProjectID";
    protected String strContractID = "ContractID";
    private String testRunUrl = null;
    private String awsUrl = "";
    private String awsFileName = "";
    private static boolean isAccessibilityTestingEnabled;
    private static boolean isScratchBuildSetup;
    private static boolean isModuleHealthLoggingEnabled;
    private static String projectName;
    private static List<ContextualHelp> contextualHelpMap;
    protected String pathToDownloadedFile;
    private static boolean isCodeCoverageEnabled;
    private static boolean findImpactedTests;
    private String allureAWSURL = "";
    private String allureAWSFileName = "";


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        executionTimeStamp = DateTimeUtil.getCurrentDate("dd/MM/YYYY HH:mm:ss");
        EnvironmentHelper.loadProperties();

        if (getClass().getName().contains(".webUI.")) {
            requiresWebDriver = true;
            initBrowserType();
        }
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String strDate = formatter.format(today);
        String logFilePath = System.getProperty("LogFilePath");
        if (Strings.isNullOrEmpty(logFilePath)) {
            logFilePath = Paths.get(userDir, Constants.REPORTS_FOLDER_PATH, strDate, "/").toString();
            System.setProperty("LogFilePath", logFilePath);
        }

        String logFileLocation = Paths.get(logFilePath, "AutomationReport.html").toString();
        formatter = new SimpleDateFormat("dd MMMM yyyy");
        strDate = formatter.format(today);

        htmlReporter = new ExtentSparkReporter(logFileLocation);
        htmlReporter.config().setDocumentTitle("Automation Report " + strDate);
        htmlReporter.config().setReportName("Automation Report " + strDate);
        htmlReporter.config().setTheme(Theme.STANDARD);

        report = new ExtentReports();
        report.attachReporter(htmlReporter);


        if (isAzureLoggingEnabled) {
            try {
                String runTitle = "Automation Run-" + DateTimeUtil.getCurrentDateTime();
                if (requiresWebDriver) {
                    runTitle = String.format("%s | %s Execution | %s", currentEnvironment.getName(), runType, DateTimeUtil.getCurrentDate("dd MMMM yyyy HH:mm:ss"));
                }
                testRunId = azureLogger.createTestRun(testCaseIds, runTitle);
                assertHelper = new AssertHelper(azureLogger, testRunId);
            } catch (Exception ex) {
                logger.error("Exception occurred while creating a test run" + ex.getMessage());
            }
        } else {
            //Sending negative value of test run to signify azure logging is disabled.
            assertHelper = new AssertHelper(azureLogger, -1);
        }

        // Kill any existing drivers.
        if (requiresWebDriver)
            killDrivers(browserType);

        // This would handle folder creation for both API and Web UI.
        //FileHelper.createFolder(fileDownloadPath, false);
        //System.setProperty(CURRENT_ENV.getValue(), currentEnvironment.getName());
        //System.setProperty(CURRENT_RUN_TYPE.getValue(), runType);
    }


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        if (DriverManager.getDriver() == null && driver != null) {
            DriverManager.setDriver(driver);
        }
        var testName = method.getAnnotation(Test.class).testName();
        var suiteName = method.getAnnotation(Test.class).suiteName();

        if (isTestAlreadyLogged(testName)) {
            testName = String.format("Retrying - %s", testName);
        }
        String browser = EnvironmentHelper.getPropertyValue("browser");
        if(browser.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();
            driver.get(EnvironmentHelper.getPropertyValue("url"));
            driver.manage().window().maximize();
        }
        ExtentTest extentTest = startTest(testName, this.getClass().getName());
        test.set(extentTest);
        extentTest.assignCategory(suiteName);

        var testCaseIds = method.getDeclaredAnnotation(TestInfo.class).testIds();
        if (isAzureLoggingEnabled) {
            if (azureLogger == null) {
                azureLogger = new AzureLogger(projectName);
            }
            try {
                azureLogger.updateTestResult(testRunId, testCaseIds, AzureState.InProgress, AzureResults.InProgress, "");
            } catch (Exception ex) {
                logger.error("BeforeMethod:Exception occurred while updating a test result " + ex.getMessage());
            }
        }
        if (isCodeCoverageEnabled) {
            CodeCoverageHelper.startCoverage(currentBuild.getUrl());
        }

        webDriverUtil = Objects.requireNonNullElseGet(webDriverUtil, WebDriverUtil::new);
        if (requiresWebDriver) {
            var downloadPathName = method.getDeclaredAnnotation(TestInfo.class).downloadPath();
            pathToDownloadedFile = Strings.isNotNullAndNotEmpty(downloadPathName) ? Paths.get(fileDownloadPath, downloadPathName).toString() : fileDownloadPath;
            if (Strings.isNotNullAndNotEmpty(downloadPathName)) {
                // This 'pathToDownloadedFile' to be used inside the test method to be passed to Get File.
                pathToDownloadedFile = Paths.get(fileDownloadPath, downloadPathName).toString();
            } else {
                pathToDownloadedFile = fileDownloadPath;
            }
           // driver = webDriverUtil.getChromeDriver();
            if (driver != null) {
                DriverManager.setDriver(driver);
            } else {
                extentTest.fail("Failed to create a web driver instance!");
            }
        }
        if (requiresWebDriver && driver == null) {
            extentTest.fail("Failed to create a web driver instance!");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        String screenShotBase64String = "";
        var testCaseIds = result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(TestInfo.class).testIds();
        AzureResults outcome = AzureResults.Blocked;
        String comment = null;
        if (result.getStatus() == ITestResult.FAILURE) {
            comment = result.getThrowable().getMessage();
            if (requiresWebDriver && driver != null) {
                screenShotBase64String = GetInstance(ScreenshotHelper.class, driver).takeFullScreenshotAndReturnBase64String(comment);
            }
            ExceptionHandler.log(result.getThrowable());
            logger().fail("Test failed");

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            outcome = AzureResults.Passed;
            logger().pass("Test passed");
        } else {
            try {
                comment = result.getThrowable().getMessage();
                logger().skip(comment);
            } catch (Exception ex) {
                logger.error(String.format("Exception occurred while fetching error logs for a skipped test:%s", ex.getMessage()));
            }
            logger().skip("Test skipped");
            if (requiresWebDriver && driver != null) {
                screenShotBase64String = GetInstance(ScreenshotHelper.class, driver).takeFullScreenshotAndReturnBase64String(comment);
            }
        }
        if (isAzureLoggingEnabled) {
            if (azureLogger == null) {
                azureLogger = new AzureLogger(projectName);
            }
            try {
                var pendingTests = azureLogger.getPendingTests(testRunId, testCaseIds);
                List<String> pendingList = new ArrayList<>();
                pendingTests.forEach(x -> pendingList.add(String.format("%s:%s", x.getTestCase().getId(), x.getTestCase().getName())));
                if (pendingList.size() > 0) {
                    var listToPrint = div(div(b(u("Blocked Tests"))),
                            each(pendingList, TagCreator::div))
                            .render();
                    logger().info(listToPrint);
                }
                azureLogger.updateTestResultWithScreenshot(testRunId, testCaseIds, AzureState.Completed, outcome, comment, screenShotBase64String);
            } catch (Exception ex) {
                logger.error("AfterMethod:Exception occurred while updating a test result " + ex.getMessage());
            }
        }
        var method = result.getMethod().getConstructorOrMethod().getMethod();

        if (isCodeCoverageEnabled) {
            CodeCoverageHelper.stopCoverage(currentBuild.getUrl());
            CodeCoverageHelper.createTestToClassMap(method.getName());
        }

        if (requiresWebDriver) {
            driver = DriverManager.getDriver();
            if (driver != null) {
                driver.quit();
            }
        }
        flushReport();
    }


    @AfterSuite(alwaysRun = true)
    public synchronized void afterSuite() {
        flushReport();
    }

    /**
     * Method to fetch a POM page instance
     *
     * @param page    POM page class
     * @param <TPage> POM page class type
     * @return a new POM page instance, if the instance is null, else return the existing instance
     */
    public <TPage extends BasePage> TPage getPage(Class<TPage> page) {
        WebDriver driver = DriverManager.getDriver();
        return GetInstance(page, driver);
    }

    /**
     * Flush extent report
     */
    private static void flushReport () {
        try {
            report.flush();
        } catch (Exception ex) {
            logger.error("Error occurred while flushing the report -" + ex.getMessage());
        }
    }

    /**
     * Kills the open Driver instances
     *
     * @param browserType -   Browser type.
     */
    private void killDrivers(BrowserType browserType) {
        try {
            switch (browserType) {
                case Chrome:
                    //Killing all open chrome driver, if any.
                    Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
                    break;
                case Edge:
                    //Killing all open MS Edge driver, if any.
                    Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception occurred wile killing the stray driver instances" + e.getMessage());
            logger.error("Exception occurred wile killing the stray driver instances" + e.getMessage());
        }
    }
}

