package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ListPageColumnName {
    UserName("User Name"),
    IsActive("Is Active?"),
    WorkflowStatus("Workflow Status");

    private String value;

    private ListPageColumnName(String value) {
        this.value = value;
    }

    // getter method
    public String getValue() {
        return this.value;
    }
}
