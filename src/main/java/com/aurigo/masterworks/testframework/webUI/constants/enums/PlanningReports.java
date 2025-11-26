package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PlanningReports {

    ProgramBudgetVsFundForecastReport("Program Budget Vs Fund Forecast Report"),
    ProgramForecastReport("Program Forecast Report"),
    WorkProjectsUnderProjectsReport("Work Projects Under Projects Report"),
    ProgramForecastComparisonReport("Program Forecast Comparison Report"),
    ProgramFundForecastReport("Program Fund Forecast Report"),
    ProgramSummaryByFundReport("Program Summary by Fund Report"),
    ProjectForecastComparisonReport("Project Forecast Comparison Report"),
    ProjectForecastReport("Project Forecast Report"),
    FundAvailabilityVSProjectRequirementReport("Fund Availability v/s Project Requirement Report"),
    ProjectFundForecastReport("Project Fund Forecast Report"),
    ProgramForecastDetailsReport("Program Forecast Details Report"),
    ProgramBudgetVsFundForecast("Program Budget Vs Fund Forecast");

    private String value;

    private PlanningReports(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Planning Fund List Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PlanningReports.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
