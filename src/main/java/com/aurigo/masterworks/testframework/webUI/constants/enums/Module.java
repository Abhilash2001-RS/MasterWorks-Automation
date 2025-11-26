package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum Module {

    Home("Home"),
    Planning("Planning"),
    Projects("Projects"),
    LandBank("Land Bank"),
    Library("Library"),
    WorkOrdersAndContracts("Work Orders and Contracts"),
    CustomModule("Custom Module"),
    Administration("Administration");

    private String value;

    Module(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
