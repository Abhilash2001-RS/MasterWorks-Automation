package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum GlobalFundListDocumentsColumns {
    DocumentName("Document Name"),
    Title("Title"),
    Type("Type"),
    Size("Size"),
    CreatedBy("Created By"),
    CreatedOn("Created On"),
    Version("Version"),
    CheckedOutTo("Checked Out To"),
    RecordIdentifier("Record Identifier");

    private String value;

    private GlobalFundListDocumentsColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Global Fund List Documents Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : GlobalFundListDocumentsColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
