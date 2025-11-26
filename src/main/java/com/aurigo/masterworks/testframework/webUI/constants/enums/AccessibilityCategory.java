package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum AccessibilityCategory {

    Violations("VIOLATIONS :\n"),
    JsonViolations("violations"),
    Url("\nURL : "),
    JsonUrl("url");


    private String value;

    AccessibilityCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
