package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PunchListPageColumns {

    ID("ID"),
    PunchItem("Punch Item"),
    Description("Description"),
    DueDate("Due Date"),
    ESRISaveStatus("ESRI Save Status"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    // declaring private variable for getting values
    private String value;

    PunchListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of NeedsListPageColumnTitles items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : PunchListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
