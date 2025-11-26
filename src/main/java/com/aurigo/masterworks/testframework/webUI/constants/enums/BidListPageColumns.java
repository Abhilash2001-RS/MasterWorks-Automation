package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum BidListPageColumns {
    Contractor("Contractor"),
    TotalInDollar("Total in $"),
    PercentageOverLowBid("% Over Low Bid"),
    PercentageEstimate("% Estimate"),
    Rank("Rank"),
    WorkflowStatus("WorkflowStatus"),
    PendingOnRole("Pending On Role(s)"),
    PendingOnUser("Pending On User(s)");

    private final String value;

    BidListPageColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
