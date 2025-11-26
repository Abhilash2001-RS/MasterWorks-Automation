package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum InflationRuleColumns {

    InflationRuleID("Inflation Rule ID"),
    Description("Description"),
    AnnualInflationRate("Annual Inflation Rate in %"),
    EffectiveFromDate("Effective from date");

    // declaring private variable for getting values
    private String value;

    InflationRuleColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Inflation Rule Columns
     */
    public static List<String> getColumns() {
        ArrayList<String> listToReturn = new ArrayList<>();
        for (var item : InflationRuleColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
