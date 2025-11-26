package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum SOQListPageColumns {
    CreatedBy("Created By"),
    CreatedOn("Created On"),
    SOQCycle("SOQ Cycle"),
    Remarks("Remarks"),
    WorkflowStatus("Workflow Status"),
    PendingOnRole("Pending On Role(s)"),
    PendingOnUser("Pending On User(s)");

    private String value;

    private SOQListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of SOQ List Page  Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : SOQListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
