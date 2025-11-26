package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.listPageFields;

import java.util.ArrayList;
import java.util.List;

public enum MoreOptions {
    CustomizeList("Customize List"),
    Attachments("Attachments"),
    AuditLog("AuditLog"),
    MarkActiveInActive("MarkActive/InActive"),
    AutoLayout("Autolayout"),
    RefreshLineNumber("Refresh Line Number"),
    StandardTables("Standard Tables"),
    Groups("Groups"),
    Export("Export");

    // declaring private variable for getting values
    private String value;

    MoreOptions(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of More Options items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MoreOptions.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
