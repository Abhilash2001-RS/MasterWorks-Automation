package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MaterialsImportPageHeaders {
    InternalID("InternalID"),
    RecordID("RecordID"),
    Contractor("Contractor"),
    Type("Type"),
    Quantity("Quantity");

    private String value;

    MaterialsImportPageHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static List<String> getList(){
        var listToReturn = new ArrayList();
        for(var name:MaterialsImportPageHeaders.values()){
            listToReturn.add(name.getValue());
        }
        return listToReturn;
    }
}
