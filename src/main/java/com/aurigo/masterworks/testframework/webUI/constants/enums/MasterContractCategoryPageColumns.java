package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MasterContractCategoryPageColumns {

    Category("Category"),
    Description("Description");

    private String value;

    private MasterContractCategoryPageColumns(String value) {
        this.value = value;
    }

    /**
     * Get column name
     *
     * @return Column name as a string
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Master Contract Category page columns
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MasterContractCategoryPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

}
