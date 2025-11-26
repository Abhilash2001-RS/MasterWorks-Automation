package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields;

import java.util.ArrayList;
import java.util.List;

public enum SelectOptions {
    Submit("Submit");

    // declaring private variable for getting values
    private String value;

    SelectOptions(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of SelectOptions items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : SelectOptions.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
