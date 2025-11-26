package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum FeatureSettingsColumns {

    ComponentName("Component Name"),
    Active("Active"),
    AdminAccess("Admin Access"),
    FormName("Form Name");

    private final String value;

    FeatureSettingsColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
