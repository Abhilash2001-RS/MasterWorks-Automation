package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ReportGallery {
    BudgetVSActualsReport("Budget vs Actuals Report"),
    DelayedProjectsBasedOnSchedule("Delayed Projects Based on Schedule"),
    MobileSyncReport("Mobile Sync Report");


    // declaring private variable for getting values
    private final String value;

    ReportGallery(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ReportGallery items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList<String>();
        for (var item : ReportOptions.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
