package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum SubmittalsGridColumns {
    SubmittalId("Submittal ID"),
    SubmittalTitle("Submittal Title"),
    RevisionNumber("Revision Number"),
    SubmittalType("Submittal Type"),
    Status("Status");

    private String value;

    SubmittalsGridColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Submittals Grid Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : SubmittalsGridColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
