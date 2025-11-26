package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum CurrentResourceHavingTitleImportColumns {
    Unit("Unit"),
    FixedPriceIn$("Fixed Price in $"),
    DirectPriceIn$("Direct Price in $/Unit"),
    InDirectPriceIn$("Indirect Price in $/Unit"),
    WorkingHoursPerDay("Working Hours per Day"),
    InternalID("InternalID"),
    ResourceID("Resource ID"),
    PhoneNumber("Phone Number"),
    EmailID("Email ID"),
    FullName("Full Name"),
    RecordID("RecordID");

    private String value;

    private CurrentResourceHavingTitleImportColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
