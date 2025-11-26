package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum PayEstimatesColumns {
    PayEstimateNumber("Pay Estimate Number"),
    FromDate("From Date"),
    ToDate("To Date"),
    CreatedOn("Created On"),
    CreatedBy("Created By"),
    BillAmount("Bill Amount ($)");

    private String value;

    PayEstimatesColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
