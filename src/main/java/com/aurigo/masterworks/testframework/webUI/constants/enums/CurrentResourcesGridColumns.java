package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum CurrentResourcesGridColumns {

    ResourceId("Resource ID"),
    FullName("Full Name"),
    PhoneNumber("Phone Number"),
    EmailId("Email ID"),
    FixedPrice("Fixed Price in $"),
    DirectPrice("Direct Price in $/Unit"),
    IndirectPrice("Indirect Price in $/Unit"),
    TotalVariablePrice("Total Variable Price in $/Unit"),
    WorkingHoursPerDay("Working Hours per Day");

    private String value;

    CurrentResourcesGridColumns(String value) {
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
        for(var name: CurrentResourcesGridColumns.values()){
            listToReturn.add(name.getValue());
        }
        return listToReturn;
    }
}
