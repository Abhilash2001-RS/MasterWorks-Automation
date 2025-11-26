package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum EnterpriseFundTransactionColumns {
    TransactionID("Transaction ID"),
    FundSourceName("Fund Source Name"),
    TransactionType("Transaction Type"),
    TransactionDate("Transaction Date"),
    AvailableAmountInFundSource("Available Amount in Fund Source($)"),
    Amount("Amount in $"),
    Notes("Notes");


    private String value;

    EnterpriseFundTransactionColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enterprise Fund Transaction Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : EnterpriseFundTransactionColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
