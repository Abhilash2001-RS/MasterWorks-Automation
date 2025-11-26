package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProgramCostColumns {

    projectCostPickerColumnValue("OriginalBudget"),
    projectInflatedCostPickerColumnValue("RevisedBudget"),
    projectCostGridColumnName("Original Budget ($)"),
    projectInflatedCostGridColumnName("Revised Budget ($)");

    // declaring private variable for getting values
    private String value;

    ProgramCostColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Program Cost Columns items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ProgramCostColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
