package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum AuditLogQuarter {

    QuarterOne("January-March"),
    QuarterTwo("April-June"),
    QuarterThree("July-September"),
    QuarterFour("October-December");
    private String value;

    AuditLogQuarter(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
