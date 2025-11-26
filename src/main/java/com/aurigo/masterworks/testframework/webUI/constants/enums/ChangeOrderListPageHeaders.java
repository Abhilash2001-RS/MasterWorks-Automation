package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ChangeOrderListPageHeaders {

    ShortDescription("Short Description"),
    AmountIn$("Amount in $"),
    ChangeOrderNumber("Change Order Number"),
    Priority("Priority"),
    WorkflowStatus("Workflow Status"),
    ApprovedOn("Approved On");

    private String value;

    ChangeOrderListPageHeaders(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Consolidated Change Order List page Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ChangeOrderListPageHeaders.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
