package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum UnitPriceSearchColumnName {

    ProjectName("Project Name"),
    EstimateID("Estimate ID"),
    PayItemNo("Pay Item No"),
    ItemDescription("Item Description"),
    Quantity("Quantity"),
    Unit("Unit"),
    UnitPricein$("Unit Price"),
    Amountin$("Amount"),
    ContractorID("Contractor ID"),
    ContractorName("Contractor name"),
    BidStatus("Bid Status"),
    SuggestedUnitPricein$("Suggested"),
    BidDate("Bid Date");


    private String value;

    UnitPriceSearchColumnName(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of UnitPriceSearchPage Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : UnitPriceSearchColumnName.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
