package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MoMLinkedObjectPickerColumn {
    Description("Description"),
    SourceForm("Source Form"),
    ContractName("Contract Name"),
    CreatedByName("Created By Name"),
    CreatedDate("Created Date");

    // declaring private variable for getting values
    private String value;

    MoMLinkedObjectPickerColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of MoMLinkedObjectPickerColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MoMLinkedObjectPickerColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
