package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum FilterHeader {
    FilterName("Filter Name"),
    Default("Default");

    private String value;

    FilterHeader(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of FilterHeaderTitles items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : FilterHeader.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
