package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProgramPickerColumns {
    Name("Name"),
    Description("Description"),
    TotalCost("Total Cost ($)"),
    RevisedTotalCost("Revised Total Cost ($)"),
    ProgramYear("Program Year");

    private String value;

    private ProgramPickerColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Program Project Picker Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ProgramPickerColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}