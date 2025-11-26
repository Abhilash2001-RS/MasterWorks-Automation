package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum DPRListPageHeader {
    ApprovedOn("Approved On"),
    DPRNumber("DPR Number"),
    Contractor("Contractor"),
    DPRDate("DPR Date"),
    PreparedBy("Prepared By"),
    Inspector("Inspector"),
    DateCreated("DateCreated"),
    ApprovedBy("Approved By"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending on Role(s)");

    private String value;

    DPRListPageHeader(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
