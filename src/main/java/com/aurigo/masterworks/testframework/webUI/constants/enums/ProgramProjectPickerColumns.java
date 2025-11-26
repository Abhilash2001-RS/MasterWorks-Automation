package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProgramProjectPickerColumns {
    Name("Name"),
    Description("Description"),
    ProgramYear("Program Year"),
    OriginalBudget("Original Budget ($)"),
    RevisedBudget("Revised Budget ($)"),
    ProjectCategory("Project Category"),
    ProjectStatus("Project Status"),
    Score("Score");

    private String value;

    private ProgramProjectPickerColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Program Picker Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ProgramProjectPickerColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}