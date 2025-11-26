package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum WorkOrderItemsPageHeader {
    Name("Name"),
    LineNumber("Line Number"),
    Description("Description"),
    Unit("Unit"),
    Quantity("Quantity"),
    UnitPriceIn$("Unit Price in $"),
    FundRule("Fund Rule"),
    Project("Project"),
    BudgetItem("Budget Item"),
    AmountIn$("Amount in $");

    private String value;

    WorkOrderItemsPageHeader(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of WorkOrderItemsPageHeader items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : WorkOrderItemsPageHeader.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
