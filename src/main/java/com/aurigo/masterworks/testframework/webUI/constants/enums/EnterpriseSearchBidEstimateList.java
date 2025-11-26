package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum EnterpriseSearchBidEstimateList {
    ProjectCode("Project Code"),
    ProjectName("Project Name"),
    EstimateID("Estimate ID"),
    EstimateName("Estimate Name"),
    Description("Description"),
    WorkType("Work Type");

    private String value;

    EnterpriseSearchBidEstimateList(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Bid Estimate Columns List in Enterprise Search
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : EnterpriseSearchBidEstimateList.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
