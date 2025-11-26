package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BidStatus {
    Submitted("Submitted"),
    Awarded("Awarded"),
    Rejected("Rejected"),
    EngineersEstimate("Engineer's Estimate");


    private String value;

    BidStatus(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of WorkFlowStatus items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : BidStatus.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}