package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum RFIPageColumns {
    RFIID("RFI ID"),
    Reason("Reason"),
    Subject("Subject"),
    RespondBy("Respond By"),
    Priority("Priority"),
    CreatedBy("Created By"),
    IdentifiedDate("Identified Date"),
    WorkFlowStatus("Workflow Status"),
    PendingOnRole("Pending On Role(s)");
    private String value;

    private RFIPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of RFI ListPage Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : RFIPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
