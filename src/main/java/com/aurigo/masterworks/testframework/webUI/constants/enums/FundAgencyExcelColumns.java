package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum FundAgencyExcelColumns {
    InternalID("InternalID"),
    RecordID("RecordID"),
    Name("Name"),
    Description("Description");

    private String value;

    FundAgencyExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of FundAgency Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : FundAgencyExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
