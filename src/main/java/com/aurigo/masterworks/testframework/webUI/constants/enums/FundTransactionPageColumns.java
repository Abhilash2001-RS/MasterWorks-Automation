package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum FundTransactionPageColumns {
    TransactionID("Transaction ID"),
    TransactionType("Transaction Type"),
    TransactionDate("Transaction Date"),
    FundName("Fund Name"),
    Amount("Amount in $"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    private FundTransactionPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Fund Transaction Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : FundTransactionPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}