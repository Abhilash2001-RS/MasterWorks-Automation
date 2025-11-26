package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum EnterpriseNeedsListPageColumnTitles {

    ID("ID"),
    Title("Title"),
    Type("Type"),
    BusinessUnitID("BusinessUnitID"),
    BusinessUnit("Business Unit"),
    WorkflowStatus("Workflow Status"),
    AssociatedProject("Associated Project"),
    StrategicGoals("Strategic Goals"),
    ExpectedCompletionDateAndYear("Expected Completion Date and Year"),
    EstimatedCost("Estimated Cost"),
    PendingOnUsers("Pending On User(s)"),
    PendingOnRoles("Pending On Role(s)");

    // declaring private variable for getting values
    private String value;

    EnterpriseNeedsListPageColumnTitles(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of NeedsListPageColumnTitles items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : EnterpriseNeedsListPageColumnTitles.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    /**
     * Get Value of the enum item
     *
     * @return - returns value of the enum item
     */
    public String getValue() {
        return this.value;
    }
}
