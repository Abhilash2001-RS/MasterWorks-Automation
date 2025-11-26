package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * List of texts of the column names in the Contract Sub Item Table
 */
public enum ContractSubItemTableColumn {

    Description("Description"),
    Notes("Notes");

    // declaring private variable for getting values
    private String value;

    ContractSubItemTableColumn(String value) {
        this.value = value;
    }

    /**
     * method to return the list of the column names
     *
     * @return list of the column names
     */
    public static List<String> getListOfColumnNames() {
        var listToReturn = new ArrayList();
        for (var item : ContractSubItemTableColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }

}
