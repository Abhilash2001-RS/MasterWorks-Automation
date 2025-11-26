package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum VendorsEvaluationCategoryListPageColumns {
    VendorCategory("Vendor Category"),
    VendorSubCategory("Vendor Sub-Category"),
    EvaluationCategory("Evaluation Category"),
    Description("Description"),
    Weightage("Weightage"),
    IsActive("Is Active");

    private String value;

    VendorsEvaluationCategoryListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Vendors Evaluation Category List Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : VendorsEvaluationCategoryListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}