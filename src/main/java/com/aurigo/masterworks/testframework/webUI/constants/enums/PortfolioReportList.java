package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum PortfolioReportList {

    DetailsReport("Details Report"),
    ListPageReport("List Page Report"),
    PortfolioCostSheetSummaryReport("Portfolio Cost Sheet Summary Report"),
    PortfolioProgressReport("Portfolio Progress Report"),
    PortfolioCostSheetDetailReport("Portfolio Cost Sheet Detail Report");

    private String value;

    PortfolioReportList(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
