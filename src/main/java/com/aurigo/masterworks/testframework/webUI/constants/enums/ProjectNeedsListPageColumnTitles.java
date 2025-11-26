package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProjectNeedsListPageColumnTitles {

    ID("ID"),
    Title("Title"),
    Type("Type"),
    BusinessUnit("Business Unit"),
    WorkflowStatus("Workflow Status"),
    StrategicGoals("Strategic Goals"),
    ExpectedCompletionDateAndYear("Expected Completion Date and Year"),
    PendingOnUsers("Pending On User(s)"),
    PendingOnRoles("Pending On Role(s)");

    // declaring private variable for getting values
    private String value;

    ProjectNeedsListPageColumnTitles(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of NeedsListPageColumnTitles items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ProjectNeedsListPageColumnTitles.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
