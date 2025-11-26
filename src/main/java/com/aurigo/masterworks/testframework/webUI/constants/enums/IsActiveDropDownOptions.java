package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum IsActiveDropDownOptions {
    Select("Select"),
    Yes("Yes"),
    No("No");

    IsActiveDropDownOptions(String value) {
        this.value = value;
    }

    private final String value;

    /**
     * Returns the list of options
     *
     * @return the list of options
     */
    public static List<String> getAll(){
        List<String> listToReturn = new ArrayList<>();
        for (var item : IsActiveDropDownOptions.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    /**
     * Return the value of a option selected
     *
     * @return the value of a option selected
     */
    public String getValue() {
        return this.value;
    }
}
