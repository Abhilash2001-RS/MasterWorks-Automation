package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PayItemExcelColumns {
    InternalID("InternalID"),
    RecordID("RecordID"),
    Name("Name"),
    Description("Description"),
    FundRule("Fund Rule"),
    AccoutingCode("Accounting Code"),
    MeasurementSystem("Measurement System"),
    Unit("Unit"),
    Quantity("Quantity"),
    BudgetItems("Budget item"),
    RateIn$("Rate in $");

    private String value;

    PayItemExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Pay Item Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PayItemExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
