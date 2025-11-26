package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum EquipmentPageColumns {

    ResourceId("Resource ID"),
    Title("Title"),
    Unit("Unit"),
    FixedPricein$("Fixed Price in $"),
    VariablePricein$("Variable Price in $/Unit");

    private String value;

    EquipmentPageColumns(String value) {
        this.value = value;
    }

    /**
     * Get value of column
     *
     * @return String value of the required column
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Get list of columns
     *
     * @return list of column names
     */
    public static List<String> getList(){
        var listToReturn = new ArrayList();
        for(var name: EquipmentPageColumns.values()){
            listToReturn.add(name.getValue());
        }
        return listToReturn;
    }
}
