package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProgramForecastListPageColumns {

    Included("Included"),
    Name("Name"),
    IsRevisionAvailable("Is Revision Available?"),
    ProjectStatus("Project Status"),
    AmountIn$("Amount in $"),
    StartDate("Start Date"),
    EndDate("End Date"),
    Before("Before"),
    After("After");

    // declaring private variable for getting values
    private String value;

    ProgramForecastListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ProgramForecastListPageColumns items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ProgramForecastListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
