package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum SubmittalListPageColumns {
    SubmittalID("Submittal ID"),
    SubmittalTitle("Submittal Title"),
    SubmittalType("Submittal Type"),
    SubmittalOwner("Submittal Owner"),
    RevisionNumber("Revision Number"),
    CreatedBy("Created By"),
    CreatedDate("Created Date"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)"),
    PendingOnUsers("Pending On User(s)");

    private String value;

    private SubmittalListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of SubmittalListPage Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : SubmittalListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}