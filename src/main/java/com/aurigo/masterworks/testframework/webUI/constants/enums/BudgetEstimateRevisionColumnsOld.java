package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BudgetEstimateRevisionColumnsOld {
    BudgetEstimateRevID("Budget Estimate Rev ID"),
    BudgetEstimateRevName("Budget Estimate Rev Name"),
    CreatedBy("Created By"),
    CreatedOn("Created On"),
    ModifiedBy("Modified By"),
    ModifiedOn("Modified On"),
    EstimateTotalIn$("Estimate Total in $"),
    InflatedEstimateTotalIn$("Inflated Estimate Total in $"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    BudgetEstimateRevisionColumnsOld(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Revision Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetEstimateRevisionColumnsOld.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
