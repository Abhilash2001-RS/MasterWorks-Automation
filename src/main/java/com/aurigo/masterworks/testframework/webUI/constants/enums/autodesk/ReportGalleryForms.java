package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk;

import java.util.ArrayList;
import java.util.List;

public enum ReportGalleryForms {

    MasterProgramBudgetVsFundForecastReport("Master Program Budget Vs Fund Forecast Report"),
    ProjectPriorityReport("Project Priority Report"),
    MasterProgramSummarybyFundReport("Master Program Summary by Fund Report"),
    ProgramForecastComparisonReport("Program Forecast Comparison Report"),
    ProgramFundForecastReport("Program Fund Forecast Report"),
    ProgramSummarybyFundReport("Program Summary by Fund Report"),
    ProjectScoreCardReport("Project Score Card Report");

    // declaring private variable for getting values
    private String value;

    ReportGalleryForms(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ReportGallery Forms Grid column names
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ReportGalleryForms.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
