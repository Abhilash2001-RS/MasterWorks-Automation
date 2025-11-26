package com.aurigo.masterworks.testframework.webUI.constants.enums;

/**
 * List of texts of the column names in the Fund Categories page
 */
public enum FundCategoriesPageColumns {
    Name("Name"),
    Description("Description");

    // declaring private variable for getting values
    private String value;

    FundCategoriesPageColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}