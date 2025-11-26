package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.firstLevelNavigation;

import java.util.ArrayList;
import java.util.List;

public enum FormBuilder {
    General("General"),
    Design("Design");

    private String value;

    private FormBuilder(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Form Builder Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : FormBuilder.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}