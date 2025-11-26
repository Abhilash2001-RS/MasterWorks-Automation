package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields;

public enum WorkflowOptions {
    Associate("Associate"),
    WorkflowUser("WorkflowUser(s)"),
    WorkflowUsers("Workflow User(s)"),
    ShowPendingOnUser("ShowPendingOnUser(s)"),
    History("History"),
    Submit("Submit"),
    BallInCourt("BallInCourt");

    // declaring private variable for getting values
    private String value;

    WorkflowOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
