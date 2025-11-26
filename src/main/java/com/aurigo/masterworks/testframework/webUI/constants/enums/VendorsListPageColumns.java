package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum VendorsListPageColumns {
    VendorID("Vendor ID"),
    Name("Name"),
    Contact("Contact"),
    Address1("Address1"),
    Address2("Address2"),
    Address3("Address3"),
    VendorCategory("Vendor Category"),
    VendorSubCategory("Vendor Sub-Category"),
    City("City"),
    ZipCode("Zip Code"),
    PhoneNumber("Phone Number"),
    FaxNumber("Fax Number"),
    EmailID("Email ID"),
    IsActive("Is Active");


    private String value;

    VendorsListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of VendorsListPage Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : VendorsListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
