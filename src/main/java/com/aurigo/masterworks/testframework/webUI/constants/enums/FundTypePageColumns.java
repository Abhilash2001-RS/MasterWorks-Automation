package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum FundTypePageColumns {

    Name("Name"),
    Description("Description");

    // declaring private variable for getting values
    private String value;

    FundTypePageColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
