package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PortfolioCostSheetSummaryReport {
    PortfolioManager("Portfolio Manager"),
    PortfolioCode("Portfolio Code"),
    CreatedDate("Created Date"),
    NumberOfProjects("No. Of Projects"),
    Description("Description"),
    TotalProjectBudgetSpentInPercent("Total Project Budget Spent in %"),
    PortfolioBudgetInDollar("Portfolio Budget in "),
    BudgetInDollar("Budget in "),
    ApprovedCommitmentsInDollar("Approved Commitments in "),
    ExpensesInDollar("Expenses in ");

    private String value;

    private PortfolioCostSheetSummaryReport(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Portfolio Cost Sheet Summary Report Fields
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PortfolioCostSheetSummaryReport.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
