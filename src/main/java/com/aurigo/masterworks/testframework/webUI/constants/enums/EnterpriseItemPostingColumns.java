package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum EnterpriseItemPostingColumns {
    ItemPostingID("Item Posting ID"),
    Contractor("Contractor"),
    Inspector("Inspector"),
    CreatedDate("Created Date"),
    CreatedBy("Created By");


    private String value;

    EnterpriseItemPostingColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enterprise Item posting Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : EnterpriseItemPostingColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
