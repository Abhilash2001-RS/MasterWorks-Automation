package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum FundSummaryReportFields {
    ReportCategory("Report Category"),
    PortfolioManager("Portfolio Manager"),
    PortfolioCode("Portfolio Code"),
    CreatedDate("Created Date"),
    NumberOfProjects("No. Of Projects");

    private String value;

    private FundSummaryReportFields(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Fund Summary Report Fields
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : FundSummaryReportFields.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
