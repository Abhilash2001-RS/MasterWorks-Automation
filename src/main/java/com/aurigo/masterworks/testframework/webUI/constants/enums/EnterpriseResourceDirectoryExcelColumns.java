package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum EnterpriseResourceDirectoryExcelColumns {
    InternalID("InternalID"),
    RecordID("RecordID"),
    ResourceId("Resource ID:"),
    FullName("Full Name:"),
    ResourceType("Resource Type :"),
    WorkingHours("Working Hours per Day :"),
    ReportingManager("ReportingManager"),
    UserName("UserName"),
    BusinessUnit("Business Unit :"),
    Department("Department :"),
    AddressLine1("Address Line 1 :"),
    AddressLine2("Address Line 2 :"),
    AddressLine3("Address Line 3 :"),
    City("City :"),
    State("State :"),
    ZipCode("Zip Code :"),
    PhoneNumber("Phone Number :"),
    FaxNumber("Fax Number :"),
    EmailId("Email ID :"),
    NonWorkingDays("Non-Working Days");

    private String value;

    EnterpriseResourceDirectoryExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ERD Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : EnterpriseResourceDirectoryExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
