package com.aurigo.masterworks.testframework.webUI;

import com.aurigo.masterworks.testframework.BaseFramework;
import com.aurigo.masterworks.testframework.utilities.DriverManager;
import com.aurigo.masterworks.testframework.utilities.helper.ElementHelper;
import com.aurigo.masterworks.testframework.utilities.helper.WaitHelper;
import com.google.common.base.Strings;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BasePage extends BaseFramework {

    protected ElementHelper elementHelper;
    protected WaitHelper waitHelper;
    private WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        elementHelper = new ElementHelper(driver);
        waitHelper = new WaitHelper(driver);
    }

    /**
     * Get Enum value
     *
     * @param enumObj -   Enum object.
     * @param <E>     -   Corresponding enum.
     * @return -   Enum value.
     */
    protected <E> String getEnumValue(E enumObj) {
        Method methodCall = null;
        try {
            Class cls = enumObj.getClass();
            methodCall = cls.getDeclaredMethod("getValue");

            return methodCall.invoke(enumObj).toString();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return Strings.nullToEmpty(null);
    }

    /**
     * Method to fetch a POM page instance
     *
     * @param page    POM page class
     * @param <TPage> POM page class type
     * @return a new POM page instance, if the instance is null, else return the existing instance
     */
    public <TPage extends BasePage> TPage getPage(Class<TPage> page) {
        return GetInstance(page, driver);
    }
}
