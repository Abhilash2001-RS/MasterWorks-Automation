package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum OriginalCeilingOption {

    OriginalCeilingValue("OriginalCeilingValue"),
    OriginalCeilingStartDate("OriginalCeilingStartDate"),
    OriginalCeilingEndDate("OriginalCeilingEndDate");

    // declaring private variable for getting values
    private String value;

    OriginalCeilingOption(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of OriginalCeilingOption items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : OriginalCeilingOption.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
