package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields;

import java.util.ArrayList;
import java.util.List;

public enum ReportOptions {
    DetailsReport("DetailsReport"),
    ListPageReport("List Page Report"),
    GlobalFundAvailabilityReport("Global Fund Availability Report"),
    UserDetailsReport("User Details Report"),
    UserAccessDetailsReport("User Access Details Report"),
    WeeklyAccessSummary("Weekly Access Summary"),
    LoginLogs("Login – Logs"),
    UserAccountLogs("User Account – Logs");

    // declaring private variable for getting values
    private String value;

    ReportOptions(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ReportOptions items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ReportOptions.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
