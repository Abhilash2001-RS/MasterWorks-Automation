package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PurchaseOrderPayItemColumn {
    Name("Name"),
    Description("Description"),
    FundRule("Fund Rule"),
    AccountingCode("Accounting Code"),
    MeasurementSystem("Measurement System"),
    Unit("Unit"),
    Quantity("Quantity"),
    RateIn$("Rate in $"),
    AmountIn$("Amount in $"),
    BudgetItem("Budget item");

    // declaring private variable for getting values
    private String value;

    PurchaseOrderPayItemColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of PurchaseOrderPayItemColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : PurchaseOrderPayItemColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
