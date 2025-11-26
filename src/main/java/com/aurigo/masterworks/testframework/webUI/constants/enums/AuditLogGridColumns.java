package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum AuditLogGridColumns {
    ParentKey("Parent Key"),
    RecordIdentifier("Record Identifier"),
    TableName("Table Name"),
    FieldName("Field Name"),
    Action("Action"),
    OldValue("Old Value"),
    NewValue("New Value"),
    ActionBy("Action By"),
    ActionOn("Action On");

    private String value;

    AuditLogGridColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Audit log Grid Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : AuditLogGridColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
