package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BudgetForecastPageColumns {

    Select("Select"),
    Name("Name"),
    StartDate("Start Date"),
    EndDate("End Date"),
    Duration("Duration"),
    Progress("Progress in %"),
    Curve("Curve"),
    Unit("Unit"),
    Amount("Amount in $"),
    RemainingAmount("Remaining Amount in $ ");

    private String value;

    BudgetForecastPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Forecast page Column List
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetForecastPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
