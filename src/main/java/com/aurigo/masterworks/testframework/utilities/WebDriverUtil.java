package com.aurigo.masterworks.testframework.utilities;

import com.aurigo.masterworks.testframework.BaseFramework;
import com.aurigo.masterworks.testframework.utilities.helper.EnvironmentHelper;
import com.aurigo.masterworks.testframework.utilities.models.Browser;
import com.aurigo.masterworks.testframework.utilities.models.environment.Build;
import com.aurigo.masterworks.testframework.utilities.models.grid.Grid;
import com.aurigo.masterworks.testframework.utilities.models.grid.Host;
import com.aurigo.masterworks.testframework.utilities.models.grid.Session;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.BrowserType;
import com.google.common.base.Stopwatch;
import com.google.gson.reflect.TypeToken;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class WebDriverUtil extends BaseFramework {


    private static final Logger logger = LogManager.getLogger(WebDriverUtil.class);
    public WebDriver driver;
    public Host node;
    private Integer loadTime = 60;//Setting the default value in case of a crash while parsing the actual value from config
    private String mobilePrimaryKey;
    private URL appiumUrl;
    private int newCommandTimeout;
    private int deviceReadyTimeout;
    private String downloadPath;

    /**
     * Initialize the driver on the basis of browser chosen
     *
     * @param build -   Build details.
     * @return -   Driver instance.
     */
    public WebDriver getDriver(Build build) {
        return getDriver(build, fileDownloadPath);
    }

    /**
     * Initialize the driver on the basis of browser chosen
     *
     * @param build -   Build details.
     * @param pathToDownloadFiles -    Path to the download files.
     * @return -   Driver instance.
     */
    public WebDriver getDriver(Build build, String pathToDownloadFiles) {
        mobilePrimaryKey = EnvironmentHelper.getPropertyValue("mobilePrimaryKey");
        newCommandTimeout = Integer.parseInt(EnvironmentHelper.getPropertyValue("newCommandTimeout"));
        deviceReadyTimeout = Integer.parseInt(EnvironmentHelper.getPropertyValue("deviceReadyTimeout"));
        downloadPath = pathToDownloadFiles;

        switch (browserType) {
            case Edge:
                driver = getEdgeDriver();
                break;
            default:
                driver = getChromeDriver();
        }
        if (driver != null) {
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            try {
                loadTime = Integer.parseInt(EnvironmentHelper.getPropertyValue("waitTimeout"));

            } catch (Exception ex) {
                logger.error("Exception occurred while parsing the wait timeout" + ex.getMessage());
                loadTime = 60;
            }
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(loadTime));
            Stopwatch stopWatch = Stopwatch.createStarted();
            driver.get(build.getUrl());
            stopWatch.stop();
            logger().info(String.format("Time taken to load the login page : %d seconds", stopWatch.elapsed(TimeUnit.SECONDS)));
        }

        return driver;
    }

    private Capabilities getBrowserCapabilities(BrowserType browserType){
        switch(browserType) {
            case Edge:
                EdgeOptions edgeOptions = new EdgeOptions();
                Map<String, Object> edgePreferences = getChromeEdgePreferences();
                edgeOptions.setExperimentalOption("prefs", edgePreferences);
                edgeOptions.setCapability("ms:edgeChromium", true);

                return edgeOptions;
            case Firefox:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("profile.default_content_settings.popups", 0);
                firefoxOptions.addPreference("download.prompt_for_download", "false");
                firefoxOptions.addPreference("safebrowsing.enabled", "true");
                firefoxOptions.addPreference("download.default_directory", downloadPath);

                return firefoxOptions;
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> chromePreferences = getChromeEdgePreferences();
                chromeOptions.setExperimentalOption("prefs", chromePreferences);
                var pageLoadStrategy=isLocalRun? PageLoadStrategy.EAGER:PageLoadStrategy.NORMAL;
                chromeOptions.setPageLoadStrategy(pageLoadStrategy);

                var proxyEnabled = System.getProperty("proxyEnabled");
                if (proxyEnabled == null) {
                    proxyEnabled = EnvironmentHelper.getPropertyValue("proxyEnabled");
                }
                if (proxyEnabled.equalsIgnoreCase("yes")) {
                    String proxyAddress = EnvironmentHelper.getPropertyValue("proxyAddress");
                    Proxy chromeProxy = new Proxy();
                    chromeProxy.setHttpProxy(proxyAddress);
                    chromeProxy.setSslProxy(proxyAddress);
                    chromeOptions.setAcceptInsecureCerts(true);
                    chromeOptions.setProxy(chromeProxy);
                    //chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    chromeOptions.addArguments("--ignore-certificate-errors");
                }
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                return chromeOptions;
        }
    }

    /**
     * Get Chrome driver instance.
     *
     * @return -   Chrome driver instance.
     */
    public WebDriver getChromeDriver() {
        ChromeOptions chromeOptions = (ChromeOptions)getBrowserCapabilities(BrowserType.Chrome);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.ANY);

        if (isParallelExecutionEnabled) {
            driver = getRemoteDriver(chromeOptions, capabilities);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        }

        return driver;
    }

    /**
     * Get Chromium Edge driver instance.
     *
     * @return -   Chromium Edge driver instance.
     */
    private WebDriver getEdgeDriver() {
        // Work In Progress
        EdgeOptions edgeOptions = (EdgeOptions)getBrowserCapabilities(BrowserType.Edge);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("MicrosoftEdge");
        capabilities.setPlatform(Platform.ANY);
        //edgeOptions.merge(capabilities);
        if (isParallelExecutionEnabled) {
            driver = getRemoteDriver(edgeOptions, capabilities);
        } else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);
        }

        return driver;
    }


    /**
     * Get Chrome/Edge preferences.
     *
     * @return -   Chrome/Edge preferences.
     */
    private Map<String, Object> getChromeEdgePreferences() {
        Map<String, Object> preferences = new Hashtable<>();

        /* Below two  preference settings will disable popup dialog when download file.*/
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", "false");
        preferences.put("safebrowsing.enabled", "true");

        return preferences;
    }

    /**
     * Get Remote Driver instance for parallel execution.
     *
     * @param options -   Driver capabilities.
     * @param capabilities  -   Desired Capabilities.
     * @return -   Driver instance.
     */
    private WebDriver getRemoteDriver(Capabilities options, DesiredCapabilities capabilities) {
        int maxRetryAttempts = 3;
        String hubUrl = EnvironmentHelper.getPropertyValue("seleniumGridHubUrl");
        try {
            do {
                try {
                    --maxRetryAttempts;

                    if (isCrossBrowserRun && deviceFarmURL != null) {
                        List<Browser> browsers = JsonUtil.deSerializeFromFile(new TypeToken<List<Browser>>() {}.getType(),
                                Constants.TEST_CLASSES_FOLDER_PATH + "browsers.json");
                        DesiredCapabilities oCap = new DesiredCapabilities();
                        if(browsers != null && browsers.size() > 0) {
                            Browser browser = getRandomBrowser(browsers);
                            logger.info(String.format("Browser: %s, Version: %s", browser.browserName, browser.browserVersion));
                            switch (browser.browserName) {
                                case "chrome":
                                    options = getBrowserCapabilities(BrowserType.Chrome);
                                    break;
                                case "MicrosoftEdge":
                                    options = getBrowserCapabilities(BrowserType.Edge);
                                    break;
                                case "firefox":
                                    options = getBrowserCapabilities(BrowserType.Firefox);
                                    break;
                            }

                            oCap.setCapability("browserName", browser.browserName);
                            oCap.setCapability("browserVersion", browser.browserVersion);
                            //oCap.setCapability("os", browser.platform);
                            //oCap.setCapability("osVersion", browser.version);
                            //oCap.setCapability("seleniumVersion", "4.0.0");

                           /* HashMap<String, Object> browserstackOptions = new HashMap<>();
                            browserstackOptions.put("os", browser.platform);
                            browserstackOptions.put("osVersion", browser.version);
                            browserstackOptions.put("local", "false");
                            browserstackOptions.put("seleniumVersion", "4.0.0");
                            browserstackOptions.put("buildName", runType);
                            oCap.setCapability("bstack:options", browserstackOptions);*/

                            oCap.setCapability("aws:maxDurationSecs", 2400);
                            oCap.setCapability("aws:idleTimeoutSecs", 900);

                            options = options.merge(oCap);

                            driver = new RemoteWebDriver(deviceFarmURL, options);
                        }
                    } else {
                        options.merge(capabilities);
                        driver = new RemoteWebDriver(new URL(hubUrl), options);

                        // Get the session ID and query the grid to fetch the Host details, where the test is running.
                        String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
                        getHostDetails(sessionId, hubUrl);
                    }
                } catch (Exception ex) {
                    logger.error("Exception occurred while spawning a new remote driver " + ex.getMessage());
                }
            } while (driver == null && maxRetryAttempts > 0);

            if (driver == null) {
                return null;
            }

            // Handling file upload.
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        } catch (Exception e) {
            logger.error("Exception occurred while getting remote driver" + e.getMessage());
        }

        return driver;
    }

    /**
     * Open new page in driver
     *
     * @param build Build
     */
    public void getURL(Build build) {
        driver.get(build.getUrl());
    }

    /**
     * Get Host details
     * @param sessionId Session ID where the test is running.
     * @param hubUrl HUb url.
     */
    private void getHostDetails(String sessionId, String hubUrl){
        // Invoke API calls to get the session details from grid.
        RestAssuredUtil rest = new RestAssuredUtil();
        Response response = rest.doPostRequest(hubUrl + "graphql", "{\"query\":\"{ nodesInfo { nodes { id, uri, status, sessions { id, uri} }} }\"}");

        // Parse the response to Grid model.
        Grid grid = response.jsonPath().getObject("$", Grid.class);

        // Now lets query the Grid data to figure out to which node did the hub route our test to.
        Session session = grid.getData().getNodesInfo().getNodes().stream().flatMap(se -> se.getSessions().stream())
                .filter(id -> id.getId().equals(sessionId)).findFirst().orElse(null);
        if(null != session) {
            try {
                node = new Host(session.getUri());
            } catch (MalformedURLException e) {
                logger.error("Exception occurred while getting node details." + e.getMessage());
                e.printStackTrace();
            }
        }else{
            node = null;
            logger.error("Exception occurred while getting node details.");
        }
    }

    /**
     * Get Random browser from list
     * @param list Browser list
     * @return Browser
     */
    public Browser getRandomBrowser(List<Browser> list)
    {
        return list.get(ThreadLocalRandom.current().nextInt(list.size())% list.size());
    }
}
