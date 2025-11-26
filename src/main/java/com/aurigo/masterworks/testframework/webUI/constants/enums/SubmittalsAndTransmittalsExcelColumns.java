package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum SubmittalsAndTransmittalsExcelColumns {

    InternalID("InternalID"),
    RecordID("RecordID"),
    SubmittalType("Submittal Type:"),
    SubContractor("Sub Contractor:"),
    SubmittalPackageDisplay("SubmittalPackageDisplay"),
    DueDate("Due Date:"),
    SpecificationSection("Specification Section:"),
    SubmittalTitle("Submittal Title:"),
    Description("Description:"),
    SubmittalResponse("Submittal Response:"),
    ApproverComments("Approver Comments:");


    private final String value;

    SubmittalsAndTransmittalsExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Type Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : SubmittalsAndTransmittalsExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
