package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PurchaseOrderAllFields {
    PRRefNumber("PR Ref Number"),
    ProjectName("Project Name"),
    PurchaseOrderType("Purchase Order Type"),
    ProjectCode("Project Code"),
    PONo("PO No."),
    StartDate("Start Date"),
    EndDate("End Date"),
    LeadTimeInDays("Lead Time in Days"),
    VendorName("Vendor Name"),
    VendorID("Vendor ID"),
    PODescription("PO Description"),
    OriginalPOValueInDollar("Original PO Value in $"),
    RevisedPOValueInDollar("Revised PO Value in $");


    private final String value;


    PurchaseOrderAllFields(String value) {
        this.value = value;
    }

    /**
     * Method to get the value
     *
     * @return purchaseOrder field value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of values
     *
     * @return list of purchaseOrder field values
     */
    public static List<String> getListOfValues() {
        return Arrays.stream(PurchaseOrderAllFields.values()).map(PurchaseOrderAllFields::getValue).collect(Collectors.toList());
    }
}
