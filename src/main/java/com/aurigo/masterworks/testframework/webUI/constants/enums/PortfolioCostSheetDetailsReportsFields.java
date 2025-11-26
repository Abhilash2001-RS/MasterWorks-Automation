package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum PortfolioCostSheetDetailsReportsFields {
    PortfolioCode("Portfolio Code"),
    PortfolioBudgetIn$("Portfolio Budget in $"),
    PortfolioManager("Portfolio Manager"),
    CreatedDate("Created Date"),
    TotalProjectBudgetIn$("Total Project Budget in $"),
    TotalProjectBudgetSpentInPercentage("Total Project Budget Spent in %"),
    NoOfProjects("No. Of Projects");

    private String value;

    PortfolioCostSheetDetailsReportsFields(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
