package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ItemPostingTable {
    ContractID("Contract ID"),
    InternalID("InternalID"),
    RecordID("RecordID"),
    ItemPostingID("Item Posting ID:"),
    Contractor("Contractor :"),
    Inspector("Inspector :"),
    LineNo("Line No."),
    PayItemNumber("Pay Item No."),
    Description("Description"),
    PostedDate("Posted Date"),
    PostedQuantity("Posted Quantity"),
    TotalQuantity("Total Quantity"),
    RemainingQuantity("Remaining Quantity"),
    UnitPriceIn$("Unit Price in $"),
    FromStation("From Station"),
    ToStation("To Station"),
    IsComplete("Is Complete"),
    NeedAttention("Need Attention"),
    CreatedDate("Created Date");

    private String value;

    ItemPostingTable(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
