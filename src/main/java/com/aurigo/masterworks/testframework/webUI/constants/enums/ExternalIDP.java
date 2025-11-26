package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ExternalIDP {

    ADFS3("ADFS 3.0"),
    ADFS4("ADFS 4.0"),
    Azure("Azure");

    private String value;

    ExternalIDP(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
