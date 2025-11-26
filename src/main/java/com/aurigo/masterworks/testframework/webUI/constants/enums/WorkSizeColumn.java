package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum WorkSizeColumn {

    Name("Name"),
    Description("Description"),
    MinimumWorkValue("Minimum Work Value ($)"),
    MaximumWorkValue("Maximum Work Value ($)"),
    IsActive("Is Active");

    // Declaring private variable for getting values
    private String value;

    WorkSizeColumn(String value) {
        this.value = value;
    }

    /**
     * method to return the list of the column names
     *
     * @return list of the column names
     */
    public static List<String> getListOfColumnNames() {
        var listToReturn = new ArrayList();
        for (var item : WorkSizeColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
