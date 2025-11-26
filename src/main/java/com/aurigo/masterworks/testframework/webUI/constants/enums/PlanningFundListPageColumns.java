package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PlanningFundListPageColumns {
    Title("Title"),
    ProgramYear("Program Year"),
    Year1_2("Year 1-2"),
    Year2_3("Year 2-3"),
    Year3_4("Year 3-4"),
    Year4_5("Year 4-5"),
    Year5_6("Year 5-6"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    private PlanningFundListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Planning Fund List Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PlanningFundListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}