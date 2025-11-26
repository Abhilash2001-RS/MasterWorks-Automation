package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.firstLevelNavigation;

import java.util.ArrayList;
import java.util.List;

public enum GlobalFundTransaction {
    GlobalFundList("Global Fund List"),
    FundTransaction("Fund Transaction"),
    PlanningFund("Planning Fund");

    private String value;

    private GlobalFundTransaction(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Global Fund Transaction Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : GlobalFundTransaction.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}