package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ModuleGroup {
    Forms("Forms");


    private String value;

    ModuleGroup(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
