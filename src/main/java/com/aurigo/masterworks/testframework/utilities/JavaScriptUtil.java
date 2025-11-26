package com.aurigo.masterworks.testframework.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
    public static void flash(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String bgcolor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 7; i++) {
            changeColor("rgb(0,200,0)", element, driver);// 1
            changeColor(bgcolor, element, driver);// 2
        }
    }

    public static void changeColor(String color, WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
    }

    public static void drawBorder(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static void generateAlert(WebDriver driver, String message) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("alert('" + message + "')");
    }

    public static void clickElementByJS(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();", element);
    }

    public static void refreshBrowserByJS(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("history.go(0)");
    }

    public static String getTitleByJS(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String title = js.executeScript("return document.title;").toString();
        return title;
    }

    public static String getPageInnerText(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String pageText = js.executeScript("return document.documentElement.innerText;").toString();
        return pageText;
    }

    public static void scrollPageDown(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void scrollIntoView(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static String getBrowserInfo(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String uAgent = js.executeScript("return navigator.userAgent;").toString();
        return uAgent;
    }

    /**
     * Method to send value in input field
     *
     * @param driver  Webdriver
     * @param locator locator of the input field
     * @param value   value to send
     */
    public static void sendKeysUsingJS(WebDriver driver, By locator, String value) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String locatorType = locator.toString().substring(0, locator.toString().indexOf(":")).trim();
        String locatorValue = locator.toString().substring(locator.toString().indexOf(":") + 1).trim();
        switch (locatorType.split("\\.")[1].toLowerCase()) {
            case "id":
                js.executeScript("document.getElementById('" + locatorValue + "').value='" + value + "'");
                break;
            case "class":
                js.executeScript("document.getElementByClassName('" + locatorValue + "').value='" + value + "'");
                break;
            case "name":
                js.executeScript("document.getElementsByName('" + locatorValue + "')[0].value='" + value + "'");
                break;
        }
    }

    /**
     * @param driver Webdriver
     * @param id     ID locator of the input field
     * @param value  value to send
     * @deprecated - This method will be replaced by sendKeysUsingJS method
     */
    @Deprecated
    public static void sendKeysUsingJSWithID(WebDriver driver, String id, String value) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
    }


    public static String  getCurrentFrame(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return self.name");
    }

    /**
     * Executes a script in string format.
     *
     * @param driver driver used for execution.
     * @param script Script in string format.
     * @return resultant string after script execution.
     */
    public static String executeScript(WebDriver driver, String script) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript(script);
    }

}
