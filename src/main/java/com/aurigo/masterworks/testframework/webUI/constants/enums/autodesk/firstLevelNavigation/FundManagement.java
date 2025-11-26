package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.firstLevelNavigation;

import java.util.ArrayList;
import java.util.List;

public enum FundManagement {
    ProjectFundList("Project Fund List"),
    ProjectFundTransaction("Project Fund Transaction"),
    FundingRules("Funding Rules");

    private String value;

    private FundManagement(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Fund Management Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : FundManagement.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}