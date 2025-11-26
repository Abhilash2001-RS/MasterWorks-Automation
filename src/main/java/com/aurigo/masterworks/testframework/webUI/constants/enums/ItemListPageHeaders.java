package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ItemListPageHeaders {
    Name("Name"),
    Description("Description"),
    LineNumber("Line Number");

    private String value;

    ItemListPageHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

