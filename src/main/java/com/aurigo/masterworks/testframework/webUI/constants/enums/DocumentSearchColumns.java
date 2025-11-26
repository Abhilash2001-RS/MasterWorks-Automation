package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum DocumentSearchColumns {
    Projects("Projects"),
    DocumentProperty("Document Property"),
    DocumentTitle("Document Title"),
    FileViewStatus("File View Status"),
    DocumentFolder("Document Folder"),
    CreatedBy("Created By"),
    DocumentName("Document Name");


    private String value;

    DocumentSearchColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Search Document Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : DocumentSearchColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
