package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum DynamicGridColumns {

    CreatedBy("Created By"),
    CreatedOn("Created On"),
    Comments("Comments"),
    To("To"),
    Message("Message"),
    DueDate("Due Date"),
    Status("Status"),
    Response("Response"),
    ResponseDate("Response Date"),
    ResourceTitles("Resource Title(s)"),
    Equipment("Equipment"),
    Description("Description");


    private String value;

    DynamicGridColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Dynamic Grid Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : DynamicGridColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
