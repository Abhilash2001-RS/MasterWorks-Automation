package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum EnterpriseResourceTabs {

    detailTab("detailTab"),
    skillTab("skillTab"),
    workDetailTab("workDetailTab");

    private String value;

    EnterpriseResourceTabs(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
