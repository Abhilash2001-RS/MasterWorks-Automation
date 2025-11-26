package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProjectFundListPageColumns {
    ProjectFundID("Project Fund ID"),
    FundSourceName("Fund Source Name"),
    EncumberedAmount("Encumbered Amount in $"),
    AllocatedAmount("Allocated Amount in $"),
    UnAllocatedAmount("UnAllocated Amount in $"),
    ConsumedAmount("Consumed Amount in $"),
    RemainingAmount("Remaining Amount in $"),
    CreatedDate("Created Date"),
    CreatedBy("Created By"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    private ProjectFundListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Project Fund List Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ProjectFundListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}