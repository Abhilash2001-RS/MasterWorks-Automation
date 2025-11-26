package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum EnterpriseUserDetailsColumns {
    UserName("User Name"),
    FirstName("First Name"),
    MiddleName("Middle Name"),
    LastName("Last Name"),
    Email("Email"),
    Fax("Fax"),
    MobileNo("Mobile No"),
    Telephone("Telephone"),
    CertNo("Cert. No."),
    AddressLine("Address Line 1"),
    AddressLineSecond("Address Line 2"),
    AddressLineThird("Address Line 3"),
    City("City"),
    State("State"),
    Country("Country"),
    ZipCode("Zip Code"),
    AccountExpiryDate("Account Expiry Date"),
    SendEmailNotification("Send Email Notification"),
    BusinessUnit("Business Unit");


    private String value;

    EnterpriseUserDetailsColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enterprise User Details Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : EnterpriseUserDetailsColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
