package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum VendorScoringCriteriaListPageColumns
{
    VendorCategory("Vendor Category"),
    VendorSubCategory("Vendor Sub-Category"),
    EvaluationCategory("Evaluation Category"),
    Description("Description"),
    MaximumScore("Maximum Score"),
    IsActive("Is Active");

    private String value;

    VendorScoringCriteriaListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Vendors Scoring Criteria List Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : VendorScoringCriteriaListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
