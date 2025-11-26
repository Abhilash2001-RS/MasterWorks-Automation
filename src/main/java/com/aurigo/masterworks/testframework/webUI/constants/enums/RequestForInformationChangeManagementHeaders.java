package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum RequestForInformationChangeManagementHeaders {
    RFIID("RFI ID"),
    Title("Title");

    private String value;

    public String getValue() {
        return value;
    }

    RequestForInformationChangeManagementHeaders(String value) {
        this.value = value;
    }
}
