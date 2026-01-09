package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProgramRevisionPageColumns {

    ProgramName("Program Name"),
    RevisionNo("Revision No"),
    RevisionName("Revision Name"),
    RevisionType("Revision Type"),
    WorkflowStatus("Workflow Status"),
    Title("Title"),
    PendingOnRole("Pending On Role(s)");


    private String value;

    ProgramRevisionPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Program Revision page columns
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ProgramRevisionPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}