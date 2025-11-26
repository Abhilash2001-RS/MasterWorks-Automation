package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ScheduleResourceManagementReports
{
    ResourceCostSheetReport("Resource Cost Sheet Report"),
    ResourceDeficitReport("Resource Deficit Report"),
    ResourceUtilizationReport("Resource Utilization Report"),
    ResourceUtilizationSummaryReport("Resource Utilization Summary Report"),
    TitlesPendingAssignmentReport("Titles Pending Assignment Report");

    private String value;

    private ScheduleResourceManagementReports(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of report names
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ScheduleResourceManagementReports.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
