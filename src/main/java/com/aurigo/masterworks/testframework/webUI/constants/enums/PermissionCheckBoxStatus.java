package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum PermissionCheckBoxStatus {
    Checked("Checked"),
    Unchecked("Unchecked");

    private String value;

    PermissionCheckBoxStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
