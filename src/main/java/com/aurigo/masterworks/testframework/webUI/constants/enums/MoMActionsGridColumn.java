package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MoMActionsGridColumn {

    MeetingID("Meeting ID"),
    AssignedTO("Assigned To"),
    From("From"),
    ActionItem("Action Item"),
    DueDate("Due Date"),
    Status("Status"),
    Response("Response"),
    Response_Date("Response Date");

    // declaring private variable for getting values
    private String value;

    MoMActionsGridColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of MoMActionsGridColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MoMActionsGridColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
