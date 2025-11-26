package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.firstLevelNavigation;

import java.util.ArrayList;
import java.util.List;

public enum BudgetManagement {
    BudgetEstimates("Budget Estimates"),
    BudgetEstimateRevision("Budget Estimate Revision"),
    CurrentBudget("Current Budget");

    private String value;

    private BudgetManagement(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Management Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetManagement.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}