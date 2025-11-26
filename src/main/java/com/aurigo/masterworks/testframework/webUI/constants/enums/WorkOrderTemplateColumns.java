package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum WorkOrderTemplateColumns {
    Number("Number"),
    MasterContractCode("Master Contract Code"),
    AwardedConsultant("Awarded Consultant"),
    Title("Title"),
    MasterContractName("Master Contract Name"),
    MeasurementSystem("Measurement System"),
    WorkOrderManager("Work Order Manager"),
    StartDate("Start Date"),
    WorkOrderRequisitionReference("Work Order Requisition Reference"),
    WorkOrderValueIn$("Work Order value in $"),
    EndDate("End Date"),
    Description("Description");

    private String value;

    WorkOrderTemplateColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of WorkOrder template Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : WorkOrderTemplateColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
