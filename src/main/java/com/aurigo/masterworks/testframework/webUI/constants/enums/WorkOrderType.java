package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum WorkOrderType {
    Contract("Contract"),
    PurchaseOrder("Purchase Order");


    private final String value;

    /**
     * This method is used to get work order values
     *
     * @return work order values
     */
    public String getValue() {
        return this.value;
    }

    WorkOrderType(String value) {
        this.value = value;
    }

}
