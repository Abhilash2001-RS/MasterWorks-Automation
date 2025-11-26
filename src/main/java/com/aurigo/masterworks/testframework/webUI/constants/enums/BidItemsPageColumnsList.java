package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BidItemsPageColumnsList {
    Notes("Notes"),
    PayItemNo("Pay Item No"),
    Type("Type"),
    IndentLevel("Indent Level"),
    Name("Name"),
    LineNumber("Line No"),
    Description("Description"),
    AlternateGroup("Alternate Group"),
    Group("Group"),
    BudgetITem("Budget Item"),
    AccountingCode("Accounting Code"),
    Unit("Unit"),
    Quantity("Quantity"),
    UnitPrice("Unit Price in $"),
    FundRule("Fund Rule"),
    AmountIn$("Amount in $");

    private String value;

    BidItemsPageColumnsList(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Bid Items Columns List
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BidItemsPageColumnsList.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
