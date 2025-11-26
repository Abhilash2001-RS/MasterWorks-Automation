package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ExpensesFormBudgetPickerColumn {
    ContainerPath("Container Path"),
    LineNo("Line No."),
    StandardItemNo("Standard Item No."),
    Description("Description");

    // declaring private variable for getting values
    private String value;

    ExpensesFormBudgetPickerColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ExpensesFormBudgetPickerColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ExpensesFormBudgetPickerColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
