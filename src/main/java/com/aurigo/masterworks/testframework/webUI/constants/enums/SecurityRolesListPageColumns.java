package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum SecurityRolesListPageColumns {
    Role("Role"),
    Description("Description");

    private final String value;

    SecurityRolesListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of item values from the enum
     *
     * @return list of SecurityRolesListPageColumns item values
     */
    public static List<String> getValues() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : SecurityRolesListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    /**
     * Method to get the list of item names from the enum
     *
     * @return list of SecurityRolesListPageColumns item names
     */
    public static List<String> getNames() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : SecurityRolesListPageColumns.values()) {
            listToReturn.add(item.name());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
