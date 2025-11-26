package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ContractOnsiteTimeImportHeaders {

    InternalID("InternalID"),
    RecordID("RecordID"),
    Contractor("Contractor"),
    StartDate("Start Date"),
    EndDate("End Date");

    private String value;

    ContractOnsiteTimeImportHeaders(String value) {
            this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static List<String> getList(){
        var listToReturn = new ArrayList();
        for(var name:ContractOnsiteTimeImportHeaders.values()){
            listToReturn.add(name.getValue());
        }
        return listToReturn;
    }
}
