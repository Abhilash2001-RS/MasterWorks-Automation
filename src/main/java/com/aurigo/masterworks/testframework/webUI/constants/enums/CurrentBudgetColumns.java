package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum CurrentBudgetColumns {
    Name("Name"),
    LineNo("Line Number"),
    Description("Description"),
    AccountingCode("Accounting Code"),
    Unit("Unit"),
    Quantity("Quantity"),
    UnitPrice("Unit Price in $"),
    FundRule("Fund Rule"),
    Resources("Resources"),
    InflationRule("Inflation Rule"),
    InflationRate("Inflation Rate"),
    InflationInDollars("Inflation in $"),
    EffectiveFrom("Effective From"),
    StartDate("Start Date"),
    EndDate("End Date"),
    AmountInDollars("Amount in $"),
    InflatedAmountInDollar("Inflated Amount in $"),
    OpenCalendarPopUp("Open the calendar popup.");

    private String value;

    CurrentBudgetColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Type Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : CurrentBudgetColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
