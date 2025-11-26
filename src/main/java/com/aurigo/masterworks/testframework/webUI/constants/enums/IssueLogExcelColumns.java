package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum IssueLogExcelColumns {
    InternalID("InternalID"),
    RecordID("RecordID"),
    Contract("Contract:"),
    Title("Title:"),
    Description("Description:"),
    Priority("Priority:"),
    Identifier("Identifier:"),
    ImpactOn("Impact On:"),
    Owner("Owner:"),
    Type("Type:"),
    OccurredOn("Occurred On:"),
    DueOn("Due On:");

    private String value;

    IssueLogExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Issue Log Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : IssueLogExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
