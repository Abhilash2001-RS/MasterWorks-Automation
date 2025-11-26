package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum NewProject {

    Status("Status");


    private String value;

    NewProject(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
