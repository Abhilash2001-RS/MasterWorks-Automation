package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum RightOfWay {
    LANDNAME("Land Name"),
    LANDTYPE("Land Type"),
    OWNERTYPE("Owner Type"),
    AREA("Area"),
    NAME("Name"),
    SCORE("Score"),
    WEIGHTEDSCORE("Weighted Score");

    private String value;

    RightOfWay(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of RightOfWay  Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : RightOfWay.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
