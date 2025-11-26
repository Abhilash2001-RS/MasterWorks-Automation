package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum FormBuilderPageColumns {

    FormID("Form ID"),
    FormHeader("Form Header"),
    CreatedDate("Created Date"),
    LastModifiedDate("Last Modified Date"),
    Published("Published"),
    FormType("Form Type"),
    ParentFolderPath("ParentFolderPath"),
    ParentModule("Parent Module"),
    DropIfExists("Drop If Exists"),
    AuditLogEnabled("Audit Log Enabled"),
    CreatedBy("Created By"),
    LastModifiedBy("Last Modified By");

    private final String value;

    FormBuilderPageColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
