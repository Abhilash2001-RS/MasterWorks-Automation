package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ResourceTitleAllocationGrid {
    TitleID("Title ID"),
    Title("Title"),
    Unit("Unit"),
    ResourceType("Resource Type"),
    FixedPriceIn$("Fixed Price in $"),
    DirectPriceIn$("Direct Price in $/Unit"),
    InDirectPriceIn$("Indirect Price in $/Unit"),
    WorkingHoursPerDay("Working Hours per Day"),
    InternalID("InternalID"),
    RecordID("RecordID"),
    VariablePrice("Variable Price in $/Unit"),
    NoOfResources("No. of Resources"),
    UtilizationPerDay("Utilization per Day in %"),
    QuantityInUnits("Quantity in Units"),
    FullName("Full Name"),
    AmountIn$("Amount in $");

    private String value;

    private ResourceTitleAllocationGrid(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
