package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum TransmittalListPageColumns {
    TransmittalID("Transmittal ID"),
    Subject("Subject"),
    To("To"),
    Inclusions("Inclusions"),
    IssuePurpose("Issue Purpose"),
    ApprovalResponse("Approval Response"),
    WorkFlowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)"),
    PendingOnUsers("Pending On User(s)");


    private String value;

    TransmittalListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of TransmittalListPage Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : TransmittalListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
