package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields;

import java.util.ArrayList;
import java.util.List;

public enum ActiveInactiveOptions {
    MarkActive("MarkActive"),
    MarkInactive("MarkInactive");

    // declaring private variable for getting values
    private String value;

    ActiveInactiveOptions(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Active Inactive Options items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ActiveInactiveOptions.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
