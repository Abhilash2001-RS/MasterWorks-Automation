package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum FundAvailabilityVsProjectRequirementReportFields {
    ProgramName("Program Name"),
    ProjectName("Project Name"),
    RequirementInDollar("Requirement "),
    FundNames("Fund Names"),
    AllocatedInDollar("Allocated "),
    UnAllocatedInDollar("UnAllocated "),
    EncumberedInDollar("Encumbered "),
    ProjectDeficitInDollar("Project Deficit ");

    private String value;

    FundAvailabilityVsProjectRequirementReportFields(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Report Fields
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : FundAvailabilityVsProjectRequirementReportFields.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
