package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BudgetEstimateRevisionNewPageLabels {
    BudgetEstimateRevID("Budget Estimate Rev ID"),
    BudgetEstimateRevName("Budget Estimate Rev Name"),
    BudgetEstimateID("Budget Estimate ID"),
    BudgetEstimateName("Budget Estimate Name"),
    ProjectCode("Project Code"),
    ProjectName("Project Name"),
    BudgetEstimateType("Budget Estimate Type"),
    MeasurementSystem("Measurement System"),
    StandardItemList("Standard Item List"),
    EstimateTotalInDollars("Estimate Total in $"),
    InflatedEstimateTotalIn$("Inflated Estimate Total in $");

    private String value;

    BudgetEstimateRevisionNewPageLabels(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Revision New Page Labels
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetEstimateRevisionNewPageLabels.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
