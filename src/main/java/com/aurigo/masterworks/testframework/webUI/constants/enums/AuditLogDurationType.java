package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum AuditLogDurationType {

    ByDate("By Date"),
    ByMonth("By Month"),
    ByQuarter("By Quarter");


    private String value;

    AuditLogDurationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
