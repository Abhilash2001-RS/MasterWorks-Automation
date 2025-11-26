package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum WorkOrdersColumns {
    WorkOrderNo("Number"),
    Title("Title"),
    WorkOrderValueIn$("Work Order value in $"),
    Projects("Project(s)"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)"),
    WorkOrderType("Work Order Type");
    private String value;

    WorkOrdersColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of WorkOrdersColumns items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : WorkOrdersColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
