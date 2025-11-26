package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PlannedProjectGridColumnHeaders {

    ProjectName("Name"),
    ProjectCode("Project Code"),
    ProjectType("Project Type"),
    TotalScore("Project Score"),
    ProjectStatus("Project Status"),
    ProgramYear("Program Year"),
    OriginalBudget("Original Budget ($)"),
    RevisedBudget("Revised Budget ($)"),
    Commitments("Commitments ($)"),
    ActualCost("Actuals ($)");

    private String value;

    private PlannedProjectGridColumnHeaders(String value) {
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
     * @return list of Planned Project Grid column names
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PlannedProjectGridColumnHeaders.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
