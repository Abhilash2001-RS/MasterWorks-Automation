package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ContractTypeColumn {

    ContractType("Contract Type"),
    Description("Description");

    // declaring private variable for getting values
    private String value;

    ContractTypeColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ContractTypeColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ContractTypeColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
