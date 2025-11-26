package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum SystemDefinedDistributionCurves {
    Offset_triangular("Offset triangular"),
    Triangular("Triangular"),
    Back_Loaded("Back-loaded"),
    Front_loaded("Front-loaded"),
    Bell("Bell");

    private final String value;

    SystemDefinedDistributionCurves(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * This method is used to get the list of default distribution curve
     *
     * @return all default distribution curve
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : SystemDefinedDistributionCurves.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

}
