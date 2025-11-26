package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum VendorEvaluationListPageColumns {
    VendorName("VendorName"),
    CreatedBy("Created By"),
    CreatedOn("Created On"),
    VendorCategory("Vendor Category"),
    VendorSubCategory("Vendor Sub-Category"),
    ReviewStage("Review Stage"),
    TotalScore("Total Score"),
    WorkflowStatus("Workflow Status"),
    PendingOnRole("Pending On Role(s)"),
    PendingOnUsers("Pending On User(s)");

    private String value;

    VendorEvaluationListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Consolidated vendor evaluation List page Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ConsolidatedVendorEvaluationListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
