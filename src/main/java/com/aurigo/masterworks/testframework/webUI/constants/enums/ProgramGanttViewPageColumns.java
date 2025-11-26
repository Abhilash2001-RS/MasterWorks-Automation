package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ProgramGanttViewPageColumns {
    ID("ID"),
    Name("Name"),
    ProjectStatus("Project Status"),
    StartDate("Start Date"),
    EndDate("End Date"),
    Amount("Amount in $"),
    Before("Before"),
    After("After"),
    Year1("Year 1"),
    Year2("Year 2"),
    Year3("Year 3"),
    Year4("Year 4"),
    Year5("Year 5");

    private String value;

    private ProgramGanttViewPageColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
