package com.aurigo.masterworks.testframework.utilities.helper;

import com.aurigo.masterworks.testframework.BaseFramework;
import com.aurigo.masterworks.testframework.utilities.ExceptionHandler;
import com.aurigo.masterworks.testframework.utilities.JavaScriptUtil;
import com.aurigo.masterworks.testframework.utilities.LocatorUtil;
import com.aurigo.masterworks.testframework.utilities.http.HttpUtility;
import com.aurigo.masterworks.testframework.utilities.http.IHttpUtility;
import com.aurigo.masterworks.testframework.webUI.common.RibbonMenu;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aurigo.masterworks.testframework.webUI.constants.enums.RibbonIcons;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitHelper extends BaseFramework {
    private static final int timeoutIfWaitScenarioAlreadyFinished = 2;
    public static boolean isProductCrashed = false;
    private static int waitTimeout = 60; //Default value for wait, It gets override by properties file value.
    private WebDriver driver;
    private WebDriverWait wait;
    private By faceliftLoadingBanner;
    private By loadingBanner;
    private By pageTabHeader;


    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        try {
            waitTimeout = Integer.parseInt(EnvironmentHelper.getPropertyValue("waitTimeout"));
            wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeout));
        } catch (Exception e) {
            ExceptionHandler.logAndContinueWebDriverExceptions(e, "waitTimeout Property is not set. Default value " + waitTimeout + " seconds is being used.");
            wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeout));
        }
        faceliftLoadingBanner = By.cssSelector("div[id='notifyDiv'][style*='flex']");
        loadingBanner = By.xpath("//div[@id='notifyDiv']");
        pageTabHeader = LocatorUtil.getLocators("Validations.json").get("pageTabHeader");
    }

    /**
     * Waits for Element to be present.
     *
     * @param locator By Locator of element.
     */
    public void waitForElementPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for Element to be present.
     *
     * @param element expected Web element.
     */
    public void waitForElementPresent(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * wait for element to be enabled
     *
     * @param locator the element locator to be enabled
     */
    public void waitForElementToBeEnabled(By locator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                .withTimeout(Duration.ofSeconds(waitTimeout));
        fluentWait.until(x -> {
            return driver.findElement(locator).isEnabled();
        });
    }

    /**
     * Waits for the Element to be present and to be clickable.
     *
     * @param locator By Locator of element.
     */
    public void waitForElementToBePresentAndClickable(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for Frame and switch to it.
     *
     * @param locator By Locator of element.
     */
    public void waitForFrameAvailableAndSwitchToIt(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    /**
     * Waits for element to disappear.
     *
     * @param locator By Locator of element.
     */
    public void waitUntilElementDisappears(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Waits for element to be clickable.
     *
     * @param locator By Locator of element.
     */
    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for element to be clickable.
     *
     * @param element WebElement to wait for.
     */
    public void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for alert to be present.
     */
    public void waitForAlertPresent() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Waits for loading banner to disappear.
     */
    private void waitForLoadingBannerDisappears() {
        boolean isFrameSwitchingRequired = false;
        try {
            var currentFrame = JavaScriptUtil.getCurrentFrame(driver);
            if (currentFrame.equals(Constants.MAIN_CONTENT_FRAME_NAME)) {
                isFrameSwitchingRequired = true;
                driver.switchTo().defaultContent();
            }
        } catch (Exception ex) {
            ExceptionHandler.logAndContinueWebDriverExceptions(ex);
        }
        if (isLoadingDisplayed(faceliftLoadingBanner, timeoutIfWaitScenarioAlreadyFinished)) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(faceliftLoadingBanner));
        }


        if (isFrameSwitchingRequired)
            driver.switchTo().frame(Constants.MAIN_CONTENT_FRAME_NAME);
    }

    /**
     * Wait for Title of Page
     *
     * @param title Title to wait on.
     * @return Returns title after waiting.
     */
    public String waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
        return driver.getTitle();
    }

    /**
     * Waits for page to load with additional check of element clickable.
     *
     * @param locator - Locator to be clickable.
     */
    public void waitForPageToLoad(By locator) {
        waitForPageToLoad();
        waitForElementToBePresentAndClickable(locator);
    }

    /**
     * Waits for page to load with additional check of ribbon icon to be clickable.
     *
     * @param ribbonIcon -Ribbon Icon
     */
    public void waitForPageToLoad(RibbonIcons ribbonIcon) {
        waitForPageToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(GetInstance(RibbonMenu.class, driver).getRibbonIcon(ribbonIcon)));
    }

    /**
     * Waits for page to load.
     */
    public void waitForPageToLoad() {
        String pageStatusScript = "return document.readyState";
        try {
            var incomingFrame = JavaScriptUtil.getCurrentFrame(driver);
            //This is required to make sure that this method never switches from the frame the call was made from
            if (incomingFrame.equals(Constants.MAIN_CONTENT_FRAME_NAME)) {
                driver.switchTo().defaultContent();
            }
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(waitTimeout));

            //Waiting for the whole page to load. In a perfect world; Selenium internally waits for the page to load but we are not living in a perfect world, so...
            fluentWait.until(ExpectedConditions.jsReturnsValue(pageStatusScript).andThen(x -> x.equals("complete")));

            //Then we wait for the infamous loading banner to do its thing
            waitForLoadingBannerDisappears();

            //Then the content frame, which should ideally be loaded along with the loading banner. But then Murphy's law exists for a reason! So we wait!
            fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(String.format(".//iframe[@name='%s']", Constants.MAIN_CONTENT_FRAME_NAME))));

            if (!incomingFrame.equals(Constants.MAIN_CONTENT_FRAME_NAME)) {
                driver.switchTo().defaultContent();
            }
        } catch (Exception ex) {
            ExceptionHandler.logAndContinueWebDriverExceptions(ex);
        }
    }



    /**
     * Waits for page to load with a custom timeout
     *
     * @param tempoWaitTimeout - custom wait timeout value (scope is within this method)
     */
    public void waitForPageToLoadWithCustomTimeout(int tempoWaitTimeout) {
        String pageStatusScript = "return document.readyState";
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(tempoWaitTimeout));
            var incomingFrame = JavaScriptUtil.getCurrentFrame(driver);
            //This is required to make sure that this method never switches from the frame the call was made from
            if (incomingFrame.equals(Constants.MAIN_CONTENT_FRAME_NAME)) {
                driver.switchTo().defaultContent();
            }
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(waitTimeout));

            //Waiting for the whole page to load. In a perfect world; Selenium internally waits for the page to load but we are not living in a perfect world, so...
            fluentWait.until(ExpectedConditions.jsReturnsValue(pageStatusScript).andThen(x -> x.equals("complete")));

            //Then we wait for the infamous loading banner to do its thing
            waitForLoadingBannerDisappears();

            //Then the content frame, which should ideally be loaded along with the loading banner. But then Murphy's law exists for a reason! So we wait!
            fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(String.format(".//iframe[@name='%s']", Constants.MAIN_CONTENT_FRAME_NAME))));

            if (!incomingFrame.equals(Constants.MAIN_CONTENT_FRAME_NAME)) {
                driver.switchTo().defaultContent();
            }
        } catch (Exception ex) {
            ExceptionHandler.logAndContinueWebDriverExceptions(ex);
        } finally {
            wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeout));
        }
    }

    /**
     * Method to wait for the loading spinner to appear, works in tandem with the waitForLoadingSpinnerDisappear() method
     *
     * @param loadingSpinnerLocator locator of the loading spinner
     * @return true when loading spinner appears
     */
    private boolean isLoadingDisplayed(By loadingSpinnerLocator, int timeOut) {
        try {
            var tempWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            tempWait.until(ExpectedConditions.visibilityOfElementLocated(loadingSpinnerLocator));
            return driver.findElement(loadingSpinnerLocator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException
                 | org.openqa.selenium.StaleElementReferenceException
                 | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    /**
     * Method to wait for the loading spinner to disappear
     */
    public void waitForLoadingSpinnerDisappear() {
        var loadingSpinnerLocator = By.xpath(".//div[@class='k-loading-image']");
        if (isLoadingDisplayed(loadingSpinnerLocator, timeoutIfWaitScenarioAlreadyFinished)) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinnerLocator));
        }
    }

    /**
     * Method to wait for the locator to Appear
     *
     * @param locator- To be located
     */
    public void waitUntilElementAppears(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Fluent Wait
     *
     * @param locator - locator
     */
    public void fluentWait(By locator) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                .withTimeout(Duration.ofSeconds(Integer.parseInt(EnvironmentHelper.getPropertyValue("waitTimeout"))))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Method to check the presence of either of the one element
     *
     * @param locator1 - first locator
     * @param locator2 - second locator
     */
    public void waitForEitherElementPresent(By locator1, By locator2) {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(locator1),
                ExpectedConditions.presenceOfElementLocated(locator2)
        ));
    }

    /**
     * Method to check the presence of both element
     *
     * @param locator1 - first locator
     * @param locator2 - second locator
     */
    public void waitForBothElementPresent(By locator1, By locator2) {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.presenceOfElementLocated(locator1),
                ExpectedConditions.presenceOfElementLocated(locator2)
        ));
    }

    /**
     * Wait for multiple elements present
     *
     * @param locators - locators to be checked
     */
    public void waitForMultipleElements(List<By> locators) {
        Iterator locatorIterator = locators.iterator();
        while (locatorIterator.hasNext()) {
            wait.until(ExpectedConditions.presenceOfElementLocated((By) locatorIterator.next()));
        }
    }

    /**
     * Wait for all ajax calls to be completed
     */
    public void waitForAjaxToComplete() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        Stopwatch stopWatch = Stopwatch.createStarted();
        wait.until(x -> (boolean) js.
                executeScript("return jQuery.active == 0"));
        logger().info(String.format("time taken for ajax call to be completed = %d milliseconds", stopWatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * Wait for drop down options to be loaded
     *
     * @param optionToCheck   the option to be selected
     * @param dropdownElement - selector for the dropdown element
     */
    public void waitUntilDropdownOptionsPopulated(By dropdownElement, String optionToCheck) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                .withTimeout(Duration.ofSeconds(waitTimeout));
        fluentWait.until(x -> driver.findElement(dropdownElement).findElements(By.tagName("option")).stream().anyMatch(y -> y.getText().equalsIgnoreCase(optionToCheck)));
    }

    /**
     * Waits for an url to respond with http code between 200 and 399
     *
     * @param url     - url to be polled
     * @param timeOut - maximum wait timeout
     */
    public static void waitForUrlToBeAvailable(String url, int timeOut) {
        IHttpUtility httpUtility = new HttpUtility();
        FluentWait<IHttpUtility> fluentWait = new FluentWait<>(httpUtility).pollingEvery(Duration.ofSeconds(2))
                .withTimeout(Duration.ofSeconds(timeOut));
        AtomicInteger attempt = new AtomicInteger();
        fluentWait.until(x -> {
            logger().info(String.format("Waiting for %s to be available, attempt %d", url, attempt.incrementAndGet()));
            var responseCode = httpUtility.getResponseCode(url);
            return 200 <= responseCode && responseCode <= 399;
        });
    }

    /**
     * Wait for page to load and wait for the page tab header to be clickable
     */
    public void waitForPageTabHeaderToBeClickable() {
        waitForPageToLoad(pageTabHeader);
    }

    /**
     * Used to get the explicit wait
     *
     * @param time - time to wait
     * @return WebDrierWait
     */
    public WebDriverWait explicitWait(long... time) {
        if (time.length > 0) {
            return new WebDriverWait(driver, Duration.ofSeconds(time[0]));
        } else {
            return new WebDriverWait(driver, Duration.ofSeconds(waitTimeout));
        }
    }

    /**
     * This method is used to wait and click element till update the attribute value
     *
     * @param clickableElement click element
     * @param attributeElement attribute element
     * @param attributeName    attribute name
     * @param attributeValue   attribute value
     * @param maximumWait      maximum pooling timeout
     * @return true if the attribute value updated successfully else false
     */
    public boolean clickUntilAttributeValueUpdated(By clickableElement, By attributeElement, String attributeName, String attributeValue, int... maximumWait) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        int poolTiming = 60;
        if (maximumWait.length > 0) {
            poolTiming = maximumWait[0];
        }
        fluentWait.withTimeout(Duration.ofSeconds(poolTiming)).pollingEvery(Duration.ofSeconds(2));
        boolean isValueUpdated;
        try {
            isValueUpdated = fluentWait.until(checkArg -> {
                driver.findElement(clickableElement).click();
                return driver.findElement(attributeElement).getAttribute(attributeName).equalsIgnoreCase(attributeValue);
            });
        } catch (TimeoutException e) {
            return false;
        }
        return isValueUpdated;
    }

}
