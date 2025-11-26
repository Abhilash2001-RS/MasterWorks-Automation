package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum FormBuilderControlType {

    Section("Section"),
    AutoIncrement("AutoIncrement"),
    TextBox("TextBox"),
    Date("Date"),
    TabsContainer("xTabs"),
    Tab("Tab"),
    Tabs("Tabs"),
    Map("GISMap");

    private final String value;

    FormBuilderControlType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
