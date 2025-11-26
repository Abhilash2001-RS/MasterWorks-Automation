package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum EnterpriseSearchBudgetEstimateList {
    ProjectCode("Project Code"),
    ProjectName("Project Name"),
    BudgetEstimateID("Budget Estimate ID"),
    BudgetEstimateName("Budget Estimate Name"),
    BudgetEstimateType("Budget Estimate Type");

    private String value;

    EnterpriseSearchBudgetEstimateList(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Columns List in Enterprise Search
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : EnterpriseSearchBudgetEstimateList.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
