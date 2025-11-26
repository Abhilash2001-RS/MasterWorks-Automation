package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum GanttViewOptions {

    BasicView("Basic View"),
    FundSourceProjects("Fund Source - Projects"),
    FundSourcePhases("Fund Source - Phases"),
    FundCategoryProjects("Fund Category - Projects"),
    FundCategoryPhases("Fund Category - Phases");

    private String value;

    GanttViewOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
