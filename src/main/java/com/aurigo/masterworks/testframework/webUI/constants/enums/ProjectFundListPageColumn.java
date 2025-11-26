package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProjectFundListPageColumn {
    ProjectFundID("Project Fund ID"),
    FundSourceName("Fund Source Name"),
    EncumberedAmountIn$("Encumbered Amount in $"),
    AllocatedAmountIn$("Allocated Amount in $"),
    UnAllocatedAmountIn$("UnAllocated Amount in $"),
    ConsumedAmountIn$("Consumed Amount in $"),
    RemainingAmountIn$("Remaining Amount in $"),
    CreatedDate("Created Date"),
    CreatedBy("Created By"),
    WorkflowStatus("Workflow Status"),
    PendingOnRole("Pending On Role(s)");

    // declaring private variable for getting values
    private String value;

    ProjectFundListPageColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ProjectFundListPageColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ProjectFundListPageColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
