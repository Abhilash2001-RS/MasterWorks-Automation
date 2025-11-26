package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;


public enum AddStandardTaskPageColumnHeaders {

    Id("ID"),
    TaskId("Task ID"),
    TaskName("Task Name"),
    Duration("Duration"),
    Predecessor("Predecessor"),
    Resources("Resources");

    private final String value;

    AddStandardTaskPageColumnHeaders(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Add Standard Task page header name
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : AddStandardTaskPageColumnHeaders.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
