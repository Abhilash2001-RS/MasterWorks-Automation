package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PotentialChangeOrderColumns {

    PCONo("PCO No."),
    Description("Description"),
    Priority("Priority"),
    WorkFlowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    PotentialChangeOrderColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Potential Change order Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PotentialChangeOrderColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
