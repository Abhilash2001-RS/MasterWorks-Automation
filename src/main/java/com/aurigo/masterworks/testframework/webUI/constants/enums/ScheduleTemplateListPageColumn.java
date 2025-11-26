package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ScheduleTemplateListPageColumn {

    ScheduleTemplateName("Schedule Template Name"),
    Description("Description");

    private final String value;

    ScheduleTemplateListPageColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of report names
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ScheduleTemplateListPageColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
