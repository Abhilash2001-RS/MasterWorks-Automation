package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields;

public enum NewOptions {
    New("New"),
    NewContainer("NewContainer");

    // declaring private variable for getting values
    private String value;

    NewOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
