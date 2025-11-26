package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * List of texts of the column names in the Contract Item list page
 */
public enum ContractItemPageColumn {

    Name("Name"),
    LineNumber("Line Number"),
    Description("Description"),
    Group("Group"),
    BudgetItem("Budget Item"),
    AccountingCode("Accounting Code"),
    Unit("Unit"),
    Quantity("Quantity"),
    UnitPrice("Unit Price in $"),
    FundRule("Fund Rule"),
    IsComplete("IsComplete"),
    AmountIn$("Amount in $"),
    Resources("Resources");

    // declaring private variable for getting values
    private String value;

    ContractItemPageColumn(String value) {
        this.value = value;
    }

    /**
     * method to return the list of the column names
     *
     * @return list of the column names
     */
    public static List<String> getListOfColumnNames() {
        var listToReturn = new ArrayList();
        for (var item : ContractItemPageColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }

}
