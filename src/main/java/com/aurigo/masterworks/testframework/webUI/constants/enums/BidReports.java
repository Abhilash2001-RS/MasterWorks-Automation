package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BidReports {
    BidTabulationSummaryReport("Bid Tabulation Summary Report"),
    Addendums("Addendums"),
    AwardedBidderDetailsReport("Awarded Bidder Details Report"),
    BidAdvertisementReport("Bid Advertisement Report"),
    BidResponseReport("Bid Response Report"),
    BidSheetReport("Bid Sheet Report"),
    BidTabulationDetails("Bid Tabulation Details"),
    EngineersEstimateReport("Engineer's Estimate Report");

    private String value;

    BidReports(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of WorkFlowStatus items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : BidReports.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}