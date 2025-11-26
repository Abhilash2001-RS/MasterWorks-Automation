package com.aurigo.masterworks.testframework.utilities.enums;

/**
 * To specify the allure path
 */
public enum AllurePathHelper {

    CURRENT_ENV("env"),
    CURRENT_RUN_TYPE("runType");

    final String value;

    AllurePathHelper(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
