package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum PortfolioCostSheetDetailReportColumnList {
    PortfolioHierarchy("Portfolio Hierarchy"),
    ProjectCode("Project Code"),
    BudgetIn$("Budget in $"),
    ApprovedCommitmentsIn$("Approved Commitments in $"),
    ExpensesIn$("Expenses in $"),
    BudgetSpentInPercentage("Budget Spent in %");

    private String value;

    PortfolioCostSheetDetailReportColumnList(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
