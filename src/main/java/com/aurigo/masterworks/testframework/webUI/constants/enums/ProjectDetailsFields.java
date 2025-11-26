package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProjectDetailsFields {
    ProjectName("Project Name"),
    ProjectCode("Project Code"),
    Description("Description"),
    BusinessUnit("Business Unit"),
    DateCreated("Date Created"),
    StartDate("Start Date"),
    EndDate("End Date"),
    Calendar("Calendar"),
    CalendarDays("Calendar Days"),
    Owner("Owner"),
    ProjectType("Project Type"),
    ProgramCategory("Program Category"),
    ProgramYear("Program Year"),
    ProjectValue("Project Value ($)");

    // declaring private variable for getting values
    private String value;

    ProjectDetailsFields(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ProjectDetailsFields items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ProjectDetailsFields.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
