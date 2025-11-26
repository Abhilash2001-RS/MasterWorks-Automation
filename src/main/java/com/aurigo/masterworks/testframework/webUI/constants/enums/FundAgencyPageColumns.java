package com.aurigo.masterworks.testframework.webUI.constants.enums;

/**
 * List of texts of the column names in the Fund Agency page
 */
public enum FundAgencyPageColumns {

    Name("Name"),
    Description("Description");

    // declaring private variable for getting values
    private String value;

    FundAgencyPageColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}