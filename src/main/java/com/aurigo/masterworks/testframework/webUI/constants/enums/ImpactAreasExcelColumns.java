package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;


public enum ImpactAreasExcelColumns {

    InternalID("InternalID"),
    RecordID("RecordID"),
    Area("Area"),
    Severity("Severity"),
    Value("Value"),
    Description("Description");

    // declaring private variable for getting values
    private String value;

    ImpactAreasExcelColumns(String value) {
        this.value = value;
    }

    /**
     * method to return the list of the column names
     *
     * @return list of the column names
     */
    public static List<String> getListOfColumnNames() {
        var listToReturn = new ArrayList();
        for (var item : ImpactAreasExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }

}
