package com.aurigo.masterworks.testframework.utilities;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> currentDriver = new ThreadLocal<>();

    /**
     * Fetches the current driver instance
     *
     * @return web driver instance
     */
    public static  WebDriver getDriver(){
        return currentDriver.get();
    }

    /**
     * Sets the current driver instance
     * @param driver driver value to be set
     */
    public static void setDriver(WebDriver driver){
        currentDriver.set(driver);
    }
}
