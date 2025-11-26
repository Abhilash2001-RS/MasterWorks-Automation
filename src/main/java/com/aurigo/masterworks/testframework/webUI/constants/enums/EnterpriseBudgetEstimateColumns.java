package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum EnterpriseBudgetEstimateColumns {
    BudgetEstimateID("Budget Estimate ID"),
    BudgetEstimateName("Budget Estimate Name"),
    BudgetEstimateType("Budget Estimate Type"),
    MeasurementSystem("Measurement System"),
    StandardItemList("Standard Item List"),
    BudgetCreatedOn("Budget Created On"),
    BudgetCreatedBy("Budget Created By"),
    BudgetEstimateTemplate("Budget Estimate Template"),
    Description("Description");


    private final String value;

    EnterpriseBudgetEstimateColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enterprise Budget Estimate Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : EnterpriseBudgetEstimateColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
