package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum StatementOfQualificationListPageColumns {
    CreatedBy("Created By"),
    CreatedOn("Created On"),
    Remarks("Remarks"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)"),
    SOQCycle("SOQ Cycle");

    private String value;

    StatementOfQualificationListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Statement of Qualification List Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : StatementOfQualificationListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}