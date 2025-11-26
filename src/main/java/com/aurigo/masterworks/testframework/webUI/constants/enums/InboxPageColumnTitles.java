package com.aurigo.masterworks.testframework.webUI.constants.enums;


public enum InboxPageColumnTitles {
    Form("Form"),
    ProjectCode("Project Code"),
    ProjectName("Project Name"),
    Subject("Subject"),
    Date("Date");

    // declaring private variable for getting values
    private String value;

    InboxPageColumnTitles(String value) {
        this.value = value;
    }

    /**
     * Get Value of the enum item
     *
     * @return - returns value of the enum item
     */
    public String getValue() {
        return this.value;
    }
}
