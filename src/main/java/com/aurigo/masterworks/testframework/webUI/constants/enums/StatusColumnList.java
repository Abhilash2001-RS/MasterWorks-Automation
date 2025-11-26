package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum StatusColumnList {

    StatusID("StatusID"),
    Status("Status"),
    ActivationStatus("ActivationStatus");

    // declaring private variable for getting values
    private String value;

    StatusColumnList(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Status Grid column names
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : StatusColumnList.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
