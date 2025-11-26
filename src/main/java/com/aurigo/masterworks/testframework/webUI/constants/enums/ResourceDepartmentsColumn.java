package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ResourceDepartmentsColumn {

    ResourceDepartment("Resource Department"),
    ParentResourceDepartment("Parent Resource Department"),
    IsRoot("Is Root?"),
    IsActive("Is Active");

    // Declaring private variable for getting values
    private String value;

    ResourceDepartmentsColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Resource Departments columns
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ResourceDepartmentsColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
