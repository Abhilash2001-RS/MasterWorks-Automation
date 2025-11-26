package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum PermissionListForRole {
    Create("Create"),
    Edit("Edit"),
    View("View"),
    Delete("Delete"),
    Settings("Settings");

    private String value;

    PermissionListForRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
