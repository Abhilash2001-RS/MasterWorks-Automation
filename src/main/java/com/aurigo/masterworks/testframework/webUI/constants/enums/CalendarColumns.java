package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum CalendarColumns {

    Name("Name"),
    Description("Description"),
    IsActive("Is Active"),
    IsResourceCalendar("Is Resource Calendar");

    private String value;

    CalendarColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
