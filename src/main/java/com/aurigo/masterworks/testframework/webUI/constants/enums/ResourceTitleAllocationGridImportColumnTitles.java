package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ResourceTitleAllocationGridImportColumnTitles {
    Title("Title:"),
    Unit("Unit"),
    ResourceType("Resource Type:"),
    FixedPriceIn$("Fixed Price in $:"),
    DirectPriceIn$("Direct Price in $/Unit:"),
    InDirectPriceIn$("Indirect Price in $/Unit:"),
    WorkingHoursPerDay("Working Hours per Day:"),
    InternalID("InternalID"),
    ResourceID("Resource ID"),
    PhoneNumber("Phone Number"),
    EmailID("Email ID"),
    RecordID("RecordID"),
    VariablePrice("Variable Price in $/Unit"),
    NoOfResources("No. of Resources");

    private String value;

    private ResourceTitleAllocationGridImportColumnTitles(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
