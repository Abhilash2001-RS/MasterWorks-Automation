package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ItemPostingPageHeaders {

    ItemPostingID("Item Posting ID"),
    CreatedDate("Created Date"),
    Inspector("Inspector"),
    WorkflowStatus("Workflow Status");

    private String value;

    ItemPostingPageHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
