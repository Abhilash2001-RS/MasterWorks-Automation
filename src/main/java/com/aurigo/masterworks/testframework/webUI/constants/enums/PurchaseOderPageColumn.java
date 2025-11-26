package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PurchaseOderPageColumn {

    PRRefNumber("PR Ref Number"),
    PONo("PO No."),
    PurchaseOrderDate("Purchase Order Date"),
    RevisedPOValueIn$("Revised PO Value in $"),
    StartDate("Start Date"),
    LeadTimeInDays("Lead Time in Days"),
    VendorName("Vendor Name"),
    WorkflowStatus("Workflow Status"),
    PendingOnRole("Pending On Role(s)"),
    AStringExpression("AStringExpression");

    // declaring private variable for getting values
    private String value;

    PurchaseOderPageColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of PurchaseOderPageColumnTitle items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : PurchaseOderPageColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
