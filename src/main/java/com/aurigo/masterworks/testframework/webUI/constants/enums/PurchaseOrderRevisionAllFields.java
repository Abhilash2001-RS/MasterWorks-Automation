package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PurchaseOrderRevisionAllFields {
    PRRevisionRefNumber("PR Revision Ref Number"),
    ProjectName("Project Name"),
    PRRefNumber("PR Ref Number"),
    ProjectCode("Project Code"),
    PurchaseOrderType("Purchase Order Type"),
    PONo("PO No."),
    RevisionDate("Revision Date"),
    EndDate("End Date"),
    StartDate("Start Date"),
    VendorName("Vendor Name"),
    LeadTimeInDays("Lead Time in Days"),
    VendorID("Vendor ID"),
    PORevisionDescription("PO Revision Description"),
    PORevisionValueInDollar("PO Revision Value in $"),
    RevisedPOAmountInDollar("Revised PO Amount in $");


    private final String value;


    PurchaseOrderRevisionAllFields(String value) {
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
        return Arrays.stream(PurchaseOrderRevisionAllFields.values()).map(PurchaseOrderRevisionAllFields::getValue).collect(Collectors.toList());
    }
}
