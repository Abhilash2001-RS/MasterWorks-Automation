package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum SchedulePageHeader {
    ID("ID"),
    TaskID("Task ID"),
    TaskName("Task Name"),
    Duration("Duration"),
    Start("Start"),
    Finish("Finish"),
    ActualStart("Actual Start"),
    ActualFinish("Actual Finish"),
    Predecessor("Predecessor"),
    Weightage("Weightage"),
    Resources("Resources"),
    PercentComplete("% Complete");

    // declaring private variable for getting values
    private String value;

    SchedulePageHeader(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ProjectFundListPageColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : SchedulePageHeader.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
