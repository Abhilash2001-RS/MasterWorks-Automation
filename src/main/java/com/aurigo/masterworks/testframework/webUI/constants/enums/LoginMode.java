package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum LoginMode {

    Masterworks("Masterworks"),
    Mixed("Mixed"),
    ExternalIDP("External IDP");

    private String value;

    LoginMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
