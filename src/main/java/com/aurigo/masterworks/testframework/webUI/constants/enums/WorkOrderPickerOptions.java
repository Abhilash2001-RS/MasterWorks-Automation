package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum WorkOrderPickerOptions {
    MasterContractCode ("MasterContractCode"),
    WorkOrderManager ("WorkOrderManager"),
    WorkOrderItems ("WorkOrderItems");


    private String value;

    private WorkOrderPickerOptions(String value) {
        this.value = value;
    }
}
