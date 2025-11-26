package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BudgetEstimateColumnsList {
    BudgetEstimateID("Budget Estimate ID"),
    BudgetEstimateName("Budget Estimate Name"),
    BudgetEstimateType("Budget Estimate Type"),
    CreatedDate("Budget Created On"),
    CreatedBy("Budget Created By"),
    EstimateTotalInDollars("Estimate Total in $"),
    InflatedEstimateTotalInDollars("Inflated Estimate Total in $"),
    ReleaseToPlanning("Release To Planning"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    BudgetEstimateColumnsList(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Columns List
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetEstimateColumnsList.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
