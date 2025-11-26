package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum MailMergeTemplateColumns {

    Name("Name"),
    Description("Description"),
    CreatedBy("Created By"),
    CreatedDate("Created Date");

    private String value;

    MailMergeTemplateColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
