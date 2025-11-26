package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum VendorSubCategoryListPageColumns
{
    VendorCategory("Vendor Category"),
    SubCategory("Sub-Category"),
    Description("Description"),
    IsActive("Is Active");

    private String value;

    private VendorSubCategoryListPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Vendor Sub-Category List Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : VendorSubCategoryListPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
