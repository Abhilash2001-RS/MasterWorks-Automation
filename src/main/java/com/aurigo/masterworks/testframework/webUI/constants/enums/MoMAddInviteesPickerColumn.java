package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MoMAddInviteesPickerColumn {
    UserName("User Name"),
    FirstName("First Name"),
    LastName("Last Name"),
    Email("Email"),
    Department("Department"),
    Attendance("Attendance");

    // declaring private variable for getting values
    private String value;

    MoMAddInviteesPickerColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of MoMAddInviteesPickerColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MoMAddInviteesPickerColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
