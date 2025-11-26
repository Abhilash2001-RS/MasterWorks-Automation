package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ScheduleGanttPageColumns {

    LineNumber("Line No"),
    PayItemNo("Pay Item No"),
    Description("Description"),
    SubItemId("SubItemID"),
    StartDate("Start Date"),
    EndDate("End Date"),
    ProgressPercentage("Progress %");

    // declaring private variable for getting values
    private String value;

    ScheduleGanttPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Schedule Gantt Page columns
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ScheduleGanttPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
