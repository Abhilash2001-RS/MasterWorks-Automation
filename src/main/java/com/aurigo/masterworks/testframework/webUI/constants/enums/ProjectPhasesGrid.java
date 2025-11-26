package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ProjectPhasesGrid {
    Sequence("Sequence"),
    Phase("Phase"),
    Name("Name"),
    Description("Description"),
    StartDate("Start Date *"),
    Status("Status *"),
    EndDate("End Date *");

    private String value;

    private ProjectPhasesGrid(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
