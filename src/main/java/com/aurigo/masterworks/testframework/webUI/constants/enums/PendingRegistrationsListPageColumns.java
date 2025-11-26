package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PendingRegistrationsListPageColumns {
    Username("User Name"),
    FirstName("First Name"),
    LastName("Last Name"),
    Company("Company"),
    Email("Email"),
    RegistrationDate("Registration Date");

    private final String value;

    PendingRegistrationsListPageColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of item values from the enum
     *
     * @return list of SecurityRolesListPageColumns item values
     */
    public static List<String> getValues() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PendingRegistrationsListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
