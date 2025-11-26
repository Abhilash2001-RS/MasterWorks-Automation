package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields;

public enum ActionsOptions {
    GlobalGroups("GlobalGroups"),
    CopyItems("CopyItems");

    // declaring private variable for getting values
    private String value;

    ActionsOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
