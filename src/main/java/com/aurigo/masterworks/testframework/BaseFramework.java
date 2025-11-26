package com.aurigo.masterworks.testframework;

import com.aurigo.masterworks.testframework.utilities.helper.EnvironmentHelper;
import com.aurigo.masterworks.testframework.utilities.models.environment.Environment;
import com.aurigo.masterworks.testframework.webUI.BasePage;
import com.aurigo.masterworks.testframework.webUI.constants.enums.BrowserType;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestNGMethod;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class BaseFramework {

    protected static ExtentReports report;
    protected static boolean isLocalRun;
    protected static ExtentSparkReporter htmlReporter;
    public static Map<String, Integer> accessibilityTestMap = new HashMap<String, Integer>();
    protected static Environment currentEnvironment;
    protected static Set<String> buildsUsed = new HashSet<>();
    protected static HashSet<ITestNGMethod> filteredTestMethods = new HashSet<>();
    protected static boolean isParallelExecutionEnabled;
    protected static boolean isCrossBrowserRun;
    protected static boolean isProxyEnabled;
    protected static BrowserType browserType;
    protected static boolean isRetryNeeded;
    protected static boolean isCloudRelease;
    protected static int newInstancesCount = 0;
    private static final Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    private static final HashMap<WebDriver, HashMap<String, Object>> instanceMap = new HashMap<>();
    protected static String fileDownloadPath;
    protected static URL deviceFarmURL;
    protected ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static final String userDir = System.getProperty("user.dir");
    protected static String runType;

    /**
     * @return ExtentTest
     * Returns the current test instance for logging
     */
    public static synchronized ExtentTest logger(){
        return extentTestMap.get((int) Thread.currentThread().getId());
    }
    /**
     * @param className   -   Name of the test class
     * @param description -   Test class description
     * @return ExtentTest
     * Creates a new ExtentTest instance and returns it
     */
    public static synchronized ExtentTest startTest(String className, String description){
        ExtentTest test = report.createTest(className, description);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    /**
     * @param testName -   Name of the test
     * @return True if the test is already logged in the report
     * <p>
     * Checks whether the test is already logged in the report
     */
    public static synchronized boolean isTestAlreadyLogged(String testName){
        return extentTestMap.values().stream().anyMatch(x-> x.getModel().getName().toLowerCase().contains(testName.toLowerCase()));
    }

    public synchronized <TPage extends BasePage> TPage GetInstance(Class<TPage> page, WebDriver driver){
        try {
            if (driver == null) {
                logger().warning(String.format("Driver object is NULL for %s class.", page.getName()));
                return null;
            }
            Object instanceToReturn;
            if (instanceMap.containsKey(driver)) {
                if (instanceMap.get(driver).containsKey(page.getName())) {
                    instanceToReturn = instanceMap.get(driver).get(page.getName());
                } else {
                    newInstancesCount++;
                    instanceToReturn = page.getDeclaredConstructor(WebDriver.class).newInstance(driver);
                    instanceMap.get(driver).put(page.getName(), instanceToReturn);
                }
            } else {
                newInstancesCount++;
                instanceToReturn = page.getDeclaredConstructor(WebDriver.class).newInstance(driver);
                HashMap<String, Object> driverMap = new HashMap<>();
                driverMap.put(page.getName(), instanceToReturn);
                instanceMap.put(driver, driverMap);
            }
            return (TPage) instanceToReturn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Delete POM objects related to a driver instance
     *
     * @param driver Web driver instance
     */
    public synchronized void deleteInstances(WebDriver driver) {
        instanceMap.remove(driver);
    }

    /**
     * Initialize Browser type
     */
    protected void initBrowserType() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = EnvironmentHelper.getPropertyValue("browser");
        }

        browserType = BrowserType.valueOf(browser);
    }
}
