package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PortfolioManagerPickerHeader {
    UserName("User Name"),
    FirstName("First Name"),
    LastName("Last Name"),
    UserRole("User Role"),
    Email("Email"),
    Company("Company");



    private String value;

    private PortfolioManagerPickerHeader(String value) {
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
     * @return list of Portfolio Manager Headers  Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PortfolioManagerPickerHeader.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
