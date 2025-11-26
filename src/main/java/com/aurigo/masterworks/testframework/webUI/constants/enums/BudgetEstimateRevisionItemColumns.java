package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BudgetEstimateRevisionItemColumns {
    Name("Name"),
    LineNumber("Line Number"),
    ItemDescription("Description"),
    AccountingCode("Accounting Code"),
    Unit("Unit"),
    Quantity("Quantity"),
    UnitPriceInDollars("Unit Price in $"),
    FundRule("Fund Rule"),
    Resources("Resources"),
    InflationRule("Inflation Rule"),
    InflationRate("Inflation Rate"),
    EffectiveFrom("Effective From"),
    AmountInDollars("Amount in $"),
    InflatedAmountInDollars("Inflated Amount in $");

    private String value;

    BudgetEstimateRevisionItemColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Revision Item Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetEstimateRevisionItemColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
