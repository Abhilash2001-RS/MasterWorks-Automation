package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum DynamicGridForFilterAndSortColumnList {
    Column0("Column0"),
    Column1("Column1"),
    Column2("Column2"),
    Column4("Column4");

    private String value;

    DynamicGridForFilterAndSortColumnList(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Dynamic Grid Columns for filter and sort List
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : DynamicGridForFilterAndSortColumnList.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
