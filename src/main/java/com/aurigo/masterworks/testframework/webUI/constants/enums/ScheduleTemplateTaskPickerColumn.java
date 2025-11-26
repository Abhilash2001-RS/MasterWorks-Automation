package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ScheduleTemplateTaskPickerColumn {
    TaskID("Task ID"),
    TaskName("Task Name"),
    Duration("Duration"),
    Predecessors("Predecessors"),
    Resources("Resources");

    private final String value;

    ScheduleTemplateTaskPickerColumn(String taskName) {
        this.value = taskName;
    }

    public String getValue() {
        return this.value;
    }

    public static List<String> getValues() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ScheduleTemplateTaskPickerColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
