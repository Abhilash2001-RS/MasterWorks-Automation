package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum AccessType {

    View("View"),
    Create("Create"),
    Delete("Delete"),
    Edit("Edit");

    private String value;

    AccessType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
