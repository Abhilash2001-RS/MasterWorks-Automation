package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum VendorEvaluationCriteriaListPageColumns
{
    VendorCategory("Vendor Category"),
    VendorSubCategory("Vendor Sub-Category"),
    EvaluationCategory("Evaluation Category"),
    Description("Description"),
    MaximumScore("Maximum Score"),
    IsActive("Is Active");

    private String value;

    VendorEvaluationCriteriaListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Vendors Evaluation Criteria List Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : VendorEvaluationCriteriaListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
