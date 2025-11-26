package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ListPageCheckBoxFilterOptions {
    NoFilter("No Filter"),
    EqualTo("Equal To"),
    NotEqualTo("Not Equal To"),
    ClearAllFilter("Clear All Filter");

    private String value;

    private ListPageCheckBoxFilterOptions(String value) {
        this.value = value;
    }

    // getter method
    public String getValue() {
        return this.value;
    }
}
