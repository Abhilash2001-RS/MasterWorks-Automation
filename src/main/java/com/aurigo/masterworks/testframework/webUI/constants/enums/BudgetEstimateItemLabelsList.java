package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BudgetEstimateItemLabelsList {

    Container("Container"),
    PayItemNumber("Pay Item No."),
    Notes("Notes"),
    Description("Description"),
    AccountingCode("Accounting Code"),
    Unit("Unit"),
    Quantity("Quantity"),
    UnitPriceIn$("Unit Price in $"),
    FundingRule("Funding Rule"),
    InflationRule("Inflation Rule"),
    AnnualInflationRate("Annual Inflation Rate (in %)"),
    EffectiveFrom("Effective from"),
    AmountIn$("Amount in $");

    private String value;

    BudgetEstimateItemLabelsList(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Item Labels
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetEstimateItemLabelsList.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
