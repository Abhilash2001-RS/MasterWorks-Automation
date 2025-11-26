package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ForecastListPageColumnsOld {
    ForecastId("Forecast ID"),
    ForecastDescription("Forecast Description"),
    StartDate("Start Date"),
    EndDate("End Date"),
    CreatedOn("Created On"),
    CreatedBy("Created By"),
    SequenceNo("Sequence No."),
    ApprovedOn("Approved On"),
    ApprovedBy("Approved By"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)"),
    PendingOnUsers("Pending On User(s)");

    private String value;

    private ForecastListPageColumnsOld(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
