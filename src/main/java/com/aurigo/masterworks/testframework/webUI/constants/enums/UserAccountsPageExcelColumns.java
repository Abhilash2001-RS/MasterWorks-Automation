package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum UserAccountsPageExcelColumns {

    InternalID("InternalID"),
    UserName("UserName"),
    FirstName("FirstName"),
    LastName("LastName"),
    Email("Email"),
    IsActive("IsActive"),
    MobileNo("MobileNo"),
    AccountExpiryDate("AccountExpiryDate"),
    Roles("Roles"),
    BusinessUnit("Business Unit"),
    MiddleName("MiddleName"),
    CompanyName("CompanyName"),
    Address1("Address1"),
    Address2("Address2"),
    Address3("Address3"),
    City("City"),
    State("State"),
    Country("Country"),
    ZipCode("Zipcode"),
    Telephone("Telephone"),
    Fax("Fax"),
    CertNo("CertNo"),
    Type("Type"),
    Vendor("Vendor"),
    IsSMS("IsSMS"),
    SendEmail("SendEmail"),
    IsADUser("IsADUser");

    private String value;

    UserAccountsPageExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Type Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : UserAccountsPageExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}


