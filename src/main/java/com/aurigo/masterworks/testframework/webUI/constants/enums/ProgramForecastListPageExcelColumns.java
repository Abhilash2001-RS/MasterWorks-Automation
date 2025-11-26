package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProgramForecastListPageExcelColumns {

    Name("Name"),
    ProjectStatus("Project Status"),
    AmountIn$("Amount in $"),
    StartDate("Start Date"),
    EndDate("End Date"),
    Before("Before"),
    After("After");

    private String value;

    ProgramForecastListPageExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ProgramForecastExcelColumns items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ProgramForecastListPageExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
