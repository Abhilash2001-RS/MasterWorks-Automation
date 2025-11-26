package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum GlobalFundListExcelColumns {
    InternalID("InternalID"),
    RecordID("RecordID"),
    FundSourceName("Fund Source Name :"),
    FundSourceCode("Fund Source Code :"),
    FundSourceCategory("Fund Source Category :"),
    FundSourceType("Fund Source Type :"),
    FundingAgency("Funding Agency :"),
    Notes("Notes :"),
    IsActive("Is Active:");

    private String value;

    GlobalFundListExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Global Fund List Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : GlobalFundListExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
