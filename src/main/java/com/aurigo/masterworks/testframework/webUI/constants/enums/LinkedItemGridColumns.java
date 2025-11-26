package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum LinkedItemGridColumns {
    Description("Description"),
    RecordIdentifier("Record Identifier"),
    SourceForm("Source Form"),
    ContractName("Contract Name");

    private String value;

    LinkedItemGridColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
