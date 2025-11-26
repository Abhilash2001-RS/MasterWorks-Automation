package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * List of texts of the column names in the Risk Register page
 */
public enum RiskRegisterPageColumn {

    ID("ID"),
    Title("Title"),
    Contract("Contract"),
    Category("Category"),
    OwnerName("Owner Name"),
    WorkflowStatus("Workflow Status"),
    PendingOnRole("Pending On Role(s)");

    // declaring private variable for getting values
    private String value;

    RiskRegisterPageColumn(String value) {
        this.value = value;
    }

    /**
     * method to return the list of the column names
     *
     * @return list of the column names
     */
    public static List<String> getListOfColumnNames() {
        var listToReturn = new ArrayList();
        for (var item : RiskRegisterPageColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }

}
