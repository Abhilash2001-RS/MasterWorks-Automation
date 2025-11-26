package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum SchedulePageExcelColumns {

    InternalID("Internal ID"),
    Type("Type"),
    TaskID("TaskID"),
    TaskName("Task Name"),
    Duration("Duration"),
    Description("Description"),
    Notes("Notes"),
    Start("Start"),
    Finish("Finish"),
    ActualStart("Actual Start"),
    ActualFinish("Actual Finish"),
    Complete("Complete"),
    Predecessor("Predecessor"),
    PercentageComplete("% Complete"),
    Weightage("Weightage"),
    TaskPriority("Task Priority"),
    ResourceID("Resource ID"),
    ResourceTitleName("Resource Title/Name"),
    ResourceType("Resource Type"),
    ResourceUnits("Resource Units"),
    ResourceFixedPriceIn$("Resource Fixed Price in $"),
    ResourceVariablePriceIn$Unit("Resource Variable Price in $/Unit"),
    NumberOfResources("Number of Resources"),
    ResourceUtilizationPerDayInPercentage("Resource Utilization per day in %"),
    ResourceQuantityInUnits("Resource Quantity in Units"),
    ResourceAmountIn$("Resource Amount in $");

    // declaring private variable for getting values
    private String value;

    SchedulePageExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Schedule Page Excel Columns
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : SchedulePageExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
