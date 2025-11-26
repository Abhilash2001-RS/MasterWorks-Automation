package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum AuditLogGridTextBoxFields {

    ParentKey("Parent Key"),
    RecordIdentifier("LinkedField"),
    TableName("Table Name"),
    FieldName("FieldName"),
    Action("Action"),
    OldValue("OldValue"),
    NewValue("NewValue"),
    ActionBy("ActionBy"),
    ActionOn("ActionOn");

    private String value;

    AuditLogGridTextBoxFields (String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
