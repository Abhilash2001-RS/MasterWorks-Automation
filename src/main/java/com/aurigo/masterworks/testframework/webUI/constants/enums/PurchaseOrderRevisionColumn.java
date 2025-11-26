package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PurchaseOrderRevisionColumn {
    PRRevisionRefNumber("PR Revision Ref Number"),
    RevisionDate("Revision Date"),
    RevisedPOAmountin$("Revised PO Amount in $"),
    StartDate("Start Date"),
    LeadTimeinDays("Lead Time in Days"),
    VendorName("Vendor Name"),
    WorkflowStatus("Workflow Status"),
    PendingOnRole("Pending On Role(s)"),
    PORevisionValuein$("PO Revision Value in $");

    // declaring private variable for getting values
    private String value;

    PurchaseOrderRevisionColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of PurchaseOrderRevisionColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : PurchaseOrderRevisionColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }

}
