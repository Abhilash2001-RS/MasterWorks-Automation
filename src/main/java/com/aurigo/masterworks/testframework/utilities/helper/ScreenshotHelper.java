package com.aurigo.masterworks.testframework.utilities.helper;

import com.aurigo.masterworks.testframework.webUI.BasePage;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper extends BasePage {

    private WebDriver driver;

    public ScreenshotHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Method to take the screenshot of the page
     *
     * @param title - Title of the screenshot
     */
    public void takeFullScreenshot(String title) {
        try {
            logger().info(title, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64String()).build());
        } catch (Exception e) {
            logger().info("Error while taking full screenshot" + e.getMessage());
        }
    }

    /**
     * Method to take the screenshot of the page
     *
     * @param title screenshot title
     * @return base64 string of the screenshot captured
     */
    public String takeFullScreenshotAndReturnBase64String(String title) {
        String base64String = "";
        try {
            base64String = takeScreenshotAsBase64String();
            logger().info(title, MediaEntityBuilder.createScreenCaptureFromBase64String(base64String).build());
        } catch (Exception e) {
            logger().info("Error while taking full screenshot" + e.getMessage());
        }
        return base64String;
    }

    /**
     * Method to take the screenshot of an element
     *
     * @param locator   -   Locator
     * @param title - Title of the screenshot
     */
    public void takeElementScreenshot(By locator, String title) {
        try {
            logger().info(title, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotOfElementAsBase64String(locator)).build());
        } catch (Exception e) {
            logger().info("Error while taking element screenshot" + e.getMessage());
        }
    }

    /**
     * Method to take the screenshot of content frame
     */
    public void takeScreenshotOfContentFrame() {
        try {
            var xpathString = String.format(".//iframe[@name='%s']", Constants.MAIN_CONTENT_FRAME_NAME);
            driver.switchTo().defaultContent();
            logger().info("Content Frame", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotOfElementAsBase64String(By.xpath(xpathString))).build());
            driver.switchTo().frame(Constants.MAIN_CONTENT_FRAME_NAME);
        } catch (Exception e) {
            logger().info("Error while taking content frame screenshot" + e.getMessage());
            driver.switchTo().defaultContent();
        }
    }

    /**
     * Take Screenshot as Base 64 String.
     *
     * @return Returns Screenshot in Base 64 String format.
     */
    private String takeScreenshotAsBase64String() {
        String base64String = "";
        try {
            base64String = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            logger().info("Some exception occurred while taking screenshot " + e.getMessage());
        }
        return base64String;
    }

    /**
     * Take Screenshot as Base 64 String.
     *
     * @return Returns Screenshot in Base 64 String format.
     */
    private String takeScreenshotOfElementAsBase64String(By locator) {
        String base64String = "";
        try {
            base64String = elementHelper.getElement(locator).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            logger().info("Some exception occurred while taking screenshot " + e.getMessage());
        }
        return base64String;
    }
}
