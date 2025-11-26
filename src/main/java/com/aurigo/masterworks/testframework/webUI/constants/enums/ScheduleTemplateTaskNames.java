package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ScheduleTemplateTaskNames {

    NewTask("New Task");

    private final String value;

    ScheduleTemplateTaskNames(String value) {
        this.value = value;
    }


    public String getValue() {
        return this.value;
    }
}
