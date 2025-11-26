package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;


public enum EnterpriseResourceDirectory {


    ResourceID("Resource ID"),
    FullName("Full Name"),
    UserName("User Name"),
    EmailID("Email ID"),
    ResourceType("Resource Type");

    // Declaring private variable for getting values
    private String value;

    EnterpriseResourceDirectory(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Resource Departments columns
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : EnterpriseResourceDirectory.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }


}
