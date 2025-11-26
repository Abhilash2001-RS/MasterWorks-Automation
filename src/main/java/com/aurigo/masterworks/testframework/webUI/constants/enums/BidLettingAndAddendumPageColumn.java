package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum BidLettingAndAddendumPageColumn {

    ID("ID"),
    BidAdvertisementDate("Bid Advertisement Date"),
    BidOpeningDate("Bid Opening Date"),
    Type("Type"),
    Status("Status"),
    ModifiedBy("Modified By"),
    ModifiedOn("Modified On");

    private String value;

    BidLettingAndAddendumPageColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
