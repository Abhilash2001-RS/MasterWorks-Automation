package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ListPageCheckBoxFieldStatus {
    Check(true),
    Uncheck(false);

    private boolean value;

    private ListPageCheckBoxFieldStatus(boolean value) {
        this.value = value;
    }

    // getter method
    public boolean getValue() {
        return this.value;
    }
}
