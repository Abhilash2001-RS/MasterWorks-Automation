package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum WorkOrderRevisionColumns {
    RevisionID("Revision ID"),
    ShortDescription("Short Description"),
    CreatedBy("Created By"),
    CreatedOn("Created On"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    WorkOrderRevisionColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of WorkOrderRevisionColumns items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : WorkOrderRevisionColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
