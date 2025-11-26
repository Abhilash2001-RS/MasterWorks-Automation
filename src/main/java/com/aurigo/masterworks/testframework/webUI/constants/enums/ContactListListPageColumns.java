package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ContactListListPageColumns {
    ID("ID"),
    Name("Name"),
    ContactNumber("Contact Number"),
    Address1("Address1"),
    Address2("Address2"),
    Address3("Address3");

    private String value;

    private ContactListListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Contact List List Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ContactListListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }

}
