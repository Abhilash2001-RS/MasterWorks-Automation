package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ScheduleTemplateTaskTableColumn {
    ID("ID"),
    TaskID("Task ID"),
    TaskName("Task Name"),
    Duration("Duration"),
    Predecessor("Predecessor"),
    Resources("Resources");

    private final String value;

    ScheduleTemplateTaskTableColumn(String taskName) {
        this.value = taskName;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * This method is used to retrieve the list a schedule template task table name
     *
     * @return list a schedule template task table name
     */
    public static List<String> getValues() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ScheduleTemplateTaskTableColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
