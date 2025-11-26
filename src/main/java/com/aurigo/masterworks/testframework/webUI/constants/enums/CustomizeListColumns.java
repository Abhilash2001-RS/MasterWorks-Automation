package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum CustomizeListColumns {
    CreatedBy("CreatedBy"),
    TransactionType("TransactionType"),
    ProgramYear("ProgramYear"),
    BudgetEstimateName("BudgetEstimateName");

    private String value;

    private CustomizeListColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Project Fund Transaction Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : CustomizeListColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}