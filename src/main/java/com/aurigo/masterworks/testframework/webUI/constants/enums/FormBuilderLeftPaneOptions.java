package com.aurigo.masterworks.testframework.webUI.constants.enums;


public enum FormBuilderLeftPaneOptions {

    GroupLayout("Group Layout"),
    SimpleInputControls("Simple Input Controls"),
    ChildDataContainers("Child Data Containers"),
    AdvancedControls("Advanced Controls");

    private final String value;

    FormBuilderLeftPaneOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
