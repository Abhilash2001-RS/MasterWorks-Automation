package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ResourceTitleImportPageHeaders {
    InternalID("InternalID"),
    RecordID("RecordID"),
    Contractor("Contractor"),
    Type("Type"),
    Quantity("Quantity"),
    TimeIn("Time In"),
    TimeOut("Time Out");

    private String value;

    ResourceTitleImportPageHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static List<String> getList(){
        var listToReturn = new ArrayList();
        for(var name:ResourceTitleImportPageHeaders.values()){
            listToReturn.add(name.getValue());
        }
        return listToReturn;
    }
}
