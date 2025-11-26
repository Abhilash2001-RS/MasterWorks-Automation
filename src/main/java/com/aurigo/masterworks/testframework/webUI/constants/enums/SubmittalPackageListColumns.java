package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum SubmittalPackageListColumns {
    PackageID("Package ID"),
    PackageTitle("Package Title"),
    PackageOwner("Package Owner"),
    CreatedBy("Created By"),
    CreatedDate("Created Date");

    private String value;

    SubmittalPackageListColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Submittal package List Page Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : SubmittalPackageListColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
