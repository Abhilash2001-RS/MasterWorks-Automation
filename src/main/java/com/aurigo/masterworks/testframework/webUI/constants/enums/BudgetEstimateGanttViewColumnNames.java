package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BudgetEstimateGanttViewColumnNames {
    Name("Name"),
    StartDate("Start Date"),
    EndDate("End Date"),
    Duration("Duration"),
    Progress("Progress in %"),
    Predecessor("Predecessor"),
    RemainingAmountIn$("Remaining Amount in $"),
    AmountInDollar("Amount in $");

    private String value;

    BudgetEstimateGanttViewColumnNames(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Gantt View Columns List
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetEstimateGanttViewColumnNames.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
