package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ForecastListPageColumns {

    ForecastId("Forecast ID"),
    ForecastDescription("State"),
    ForecastName("Forecast Name"),
    StartDate("Start Date"),
    EndDate("End Date"),
    CreatedOn("Created On"),
    CreatedBy("Created By"),
    SequenceNo("Sequence No."),
    ApprovedOn("Approved On"),
    ApprovedBy("Approved By");
    private String value;

    private ForecastListPageColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
