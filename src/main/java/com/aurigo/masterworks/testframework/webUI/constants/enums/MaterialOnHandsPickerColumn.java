package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MaterialOnHandsPickerColumn {

    PayItemNo("Pay Item No.");

    private String value;

    MaterialOnHandsPickerColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static List<String> getList(){
        var listToReturn = new ArrayList();
        for(var name:MaterialOnHandsPickerColumn.values()){
            listToReturn.add(name.getValue());
        }
        return listToReturn;
    }


}
