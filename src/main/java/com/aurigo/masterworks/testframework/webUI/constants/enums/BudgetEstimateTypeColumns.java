package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum BudgetEstimateTypeColumns {

    BudgetEstimateType("Budget Estimate Type"),
    Description("Description");

    private String value;

    BudgetEstimateTypeColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
