package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum WorkOrderPickerColumnHeaders {
    PayItemNumber("Pay Item No.");

    private String value;

    WorkOrderPickerColumnHeaders(String value){
        this.value=value;
    }

    public String getValue(){
        return this.value;
    }
}
