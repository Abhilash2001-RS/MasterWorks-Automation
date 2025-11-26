package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum WorkFlowManagementPageColumns {
    Name("Name"),
    Description("Description"),
    AssociatedForm("Associated Form"),
    Trigger("Trigger"),
    Validation("Validation"),
    Published("Published"),
    Default("Default"),
    CreatedOn("Created On"),
    CreatedBy("Created By"),
    ModifiedOn("Modified On"),
    ModifiedBy("Modified By");

    private String value;

    WorkFlowManagementPageColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * method to return the list of the column names
     *
     * @return list of the column names
     */
    public static List<String> getListOfColumnNames() {
        List<String> columnNames = new ArrayList();
        for (var item : WorkFlowManagementPageColumns.values()) {
            columnNames.add(item.getValue());
        }
        return columnNames;
    }
}
