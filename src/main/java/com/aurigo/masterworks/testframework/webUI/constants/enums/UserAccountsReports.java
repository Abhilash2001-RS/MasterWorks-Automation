package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum UserAccountsReports {

    UserDetailsReport("User Details Report"),
    UserAccessDetailReport("User Access Details Report"),
    WeeklyAccessSummary("Weekly Access Summary"),
    LoginLogs("Login – Logs"),
    UserAccountLogs("User Account – Logs");

    // declaring private variable for getting values
    private String value;

    UserAccountsReports(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ReportOptions items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : UserAccountsReports.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
