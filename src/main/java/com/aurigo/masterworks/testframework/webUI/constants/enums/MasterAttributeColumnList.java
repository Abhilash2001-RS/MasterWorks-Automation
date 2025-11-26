package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MasterAttributeColumnList {

    Attribute("Attribute"),
    Type("Type"),
    Deactivated("DeActivated"),
    Mandatory("Mandatory");

    // declaring private variable for getting values
    private String value;

    MasterAttributeColumnList(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Master Attribute Grid column names
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MasterAttributeColumnList.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
