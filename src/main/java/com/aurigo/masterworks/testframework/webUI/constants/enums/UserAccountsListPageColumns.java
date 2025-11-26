package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum UserAccountsListPageColumns {

    UserName("User Name"),
    FirstName("First Name"),
    MiddleName("Middle Name"),
    LastName("Last Name"),
    Email("Email"),
    IsActive("Is Active?"),
    IsLocked("Is Locked?"),
    MobileNo("Mobile No"),
    SensSMS("Send SMS?"),
    SendEmail("Send Email"),
    PasswordLastChanged("Password Last Changed"),
    CertificateNumber("Certificate Number"),
    BusinessUnit("Business Unit"),
    UserType("User Type"),
    UserTypes("User Type(s)"),
    Vendor("Vendor");

    private String value;

    UserAccountsListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Type Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : UserAccountsListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }

}
