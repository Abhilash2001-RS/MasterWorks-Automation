package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BusinessUnitListPageColumns {
    BusinessUnit("Business Unit"),
    ParentBusinessUnit("Parent Business Unit"),
    IsRoot("Is Root?"),
    IsActive("Is Active");
    private String value;

    BusinessUnitListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Business Unit Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BusinessUnitListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
