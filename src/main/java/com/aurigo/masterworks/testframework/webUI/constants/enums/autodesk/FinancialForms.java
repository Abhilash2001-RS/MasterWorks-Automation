package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk;

import java.util.ArrayList;
import java.util.List;

public enum FinancialForms {

    Home("Home"),
    ProjectPhases("Project Phases"),
    ProjectScoring("Project Scoring"),
    ProjectFundList("Project Fund List"),
    ProjectFundTransaction("Project Fund Transaction"),
    BudgetEstimates("Budget Estimates"),
    BudgetEstimateRevision("Budget Estimate Revisions"),
    CurrentBudget("Current Budget"),
    Contractors("Contractors"),
    ContractItems("Contract Items"),
    ItemPosting("Item Posting"),
    ChangeOrder("Contract Change Order"),
    MaterialsOnHand("Materials On Hand"),
    PayEstimates("Pay Estimates"),
    PurchaseOrder("Purchase Order"),
    Expenses("Expenses"),
    Reports("Reports"),
    Library("Library"),
    Administration("Administration");

    // declaring private variable for getting values
    private String value;

    FinancialForms(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of FinancialForms Grid column names
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : FinancialForms.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
