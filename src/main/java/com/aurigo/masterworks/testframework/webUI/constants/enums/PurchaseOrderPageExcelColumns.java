package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PurchaseOrderPageExcelColumns {

    InternalID("InternalID"),
    RecordID("RecordID"),
    PurchaseOrderType("Purchase Order Type:"),
    PurchaseOrderDate("Purchase Order Date:"),
    StartDate("Start Date:"),
    LeadTime("Lead Time in Days:"),
    VendorID("VendorId"),
    BudgetItemName("BudgetItemName"),
    PONo("PO No.:"),
    EndDate("End Date:"),
    VendorName("Vendor Name:"),
    PODescription("PO Description:"),
    POTAndC("POTandC");

    private String value;

    PurchaseOrderPageExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Purchase order page Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PurchaseOrderPageExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
