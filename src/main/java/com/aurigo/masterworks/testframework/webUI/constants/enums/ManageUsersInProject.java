package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ManageUsersInProject {

    FirstName("First Name");


    private String value;

    ManageUsersInProject(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
