package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum InflationRuleExcelColumns {
    InternalID("InternalID"),
    RecordID("RecordID"),
    InflationRuleID("Inflation Rule ID :"),
    Description("Description :"),
    AnnualInflationRateInDollars("Annual Inflation Rate in %:"),
    EffectiveFromDate("Effective from date :");

    private String value;

    InflationRuleExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Inflation Rule Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : InflationRuleExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
