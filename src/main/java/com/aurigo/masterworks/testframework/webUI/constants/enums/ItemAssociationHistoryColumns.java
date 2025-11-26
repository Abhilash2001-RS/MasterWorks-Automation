package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ItemAssociationHistoryColumns {
    FundRule("Fund Rule"),
    AssociationDate("Associated Date"),
    DissociationDate("Dissociation Date");

    // declaring private variable for getting values
    private String value;

    ItemAssociationHistoryColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ItemAssociationHistoryColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
