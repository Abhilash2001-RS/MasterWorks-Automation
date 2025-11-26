package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MyTasksListPageColumnTitles {
    Form("Form"),
    ProjectCode("Project Code"),
    ProjectName("Project Name"),
    ContractCode("Contract Code"),
    ContractName("Contract Name"),
    RecordIdentifier("Record Identifier"),
    AssignedDate("Assigned Date"),
    DueDate("Due Date"),
    Status("Status"),
    RoutedTo("Routed To");

    // declaring private variable for getting values
    private String value;

    MyTasksListPageColumnTitles(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of MyTasksListPageColumnTitles items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MyTasksListPageColumnTitles.values()) {
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
