package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum RFPListPageColumns {
    ID("Record ID"),
    SubmittedOn("Submitted On"),
    RespondBy("Respond By"),
    Priority("Priority"),
    HighLevelCost("High Level Cost Estimate ($)"),
    HighLevelTime("High Level Time Estimate (Days)"),
    Reason("Reason"),
    WorkFlowStatus("Workflow Status"),
    PendingOnRole("Pending On Role(s)"),
    PendingOnUsers("Pending On User(s)");

    private String value;

    private RFPListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of RFP ListPage Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : RFPListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
