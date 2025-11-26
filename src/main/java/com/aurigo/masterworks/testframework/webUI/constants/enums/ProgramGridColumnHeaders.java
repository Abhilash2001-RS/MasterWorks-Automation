package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProgramGridColumnHeaders {

    ProgramName("Name"),
    ProgramCode("Code"),
    ProgramYear("Program Year"),
    OriginalBudget("Original Budget ($)"),
    RevisedBudget("Revised Budget ($)"),
    Commitments("Commitments ($)"),
    ActualCost("Actuals ($)");

    private String value;

    private ProgramGridColumnHeaders(String value) {
        this.value = value;
    }

    /**
     * Get enum value
     *
     * @return enum value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Program Program Grid column headers
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ProgramGridColumnHeaders.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
