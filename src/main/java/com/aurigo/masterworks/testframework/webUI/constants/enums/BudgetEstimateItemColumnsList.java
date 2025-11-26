package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BudgetEstimateItemColumnsList {
    Name("Name"),
    LineNumber("Line Number"),
    Description("Description"),
    AccountingCode("Accounting Code"),
    Unit("Unit"),
    Quantity("Quantity"),
    UnitPriceIn$("Unit Price in $"),
    FundRule("Fund Rule"),
    Resources("Resources"),
    InflationRule("Inflation Rule"),
    AnnualInflationRate("Annual Inflation Rate(in %)"),
    EffectiveFrom("Effective From"),
    AmountIn$("Amount in $"),
    InflatedAmountIn$("Inflated Amount in $");

    private String value;

    BudgetEstimateItemColumnsList(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Item Column List
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetEstimateItemColumnsList.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
