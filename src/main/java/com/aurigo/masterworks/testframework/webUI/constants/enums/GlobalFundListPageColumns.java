package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum GlobalFundListPageColumns {
    FundSourceID("Fund Source ID"),
    FundSourceName("Fund Source Name"),
    FundSourceCode("Fund Source Code"),
    FundType("Fund Type"),
    FundCategory("Fund Category"),
    RemainingAmount("Remaining Amount in $"),
    CreatedDate("Created Date"),
    IsActive("Is Active"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    private GlobalFundListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Global Fund List Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : GlobalFundListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}