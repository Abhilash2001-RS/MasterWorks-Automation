package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BudgetEstimateRevisionItemsNewPageLabels {

    Container("Container"),
    PayItemNumber("Pay Item No."),
    Description("Description"),
    Unit("Unit"),
    FundingRule("Funding Rule"),
    AccountingCode("Accounting Code"),
    Quantity("Quantity"),
    UnitPriceInDollars("Unit Price in $"),
    AmountInDollars("Amount in $"),
    Notes("Notes");

    private String value;

    BudgetEstimateRevisionItemsNewPageLabels(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Revision Items New Page Labels
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetEstimateRevisionItemsNewPageLabels.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
