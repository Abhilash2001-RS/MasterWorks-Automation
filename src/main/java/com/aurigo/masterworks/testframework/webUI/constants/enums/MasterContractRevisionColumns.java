package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MasterContractRevisionColumns {
    RevisionID("Revision ID"),
    ShortDescription("Short Description"),
    RevisedByUser("Created By"),
    RevisedDate("Created On"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    MasterContractRevisionColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of MasterContractRevisionColumns items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MasterContractRevisionColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
