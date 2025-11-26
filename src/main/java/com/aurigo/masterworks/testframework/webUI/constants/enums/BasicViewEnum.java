package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BasicViewEnum {
    BasicView("Basic View"),
    FundSourceProject("Fund Source - Projects"),
    FundSourcePhase("Fund Source - Phases"),
    FundCategoryProject("Fund Category - Projects"),
    FundCategoryPhase("Fund Category - Phases");

    private String value;

    BasicViewEnum(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of BasicViewEnum
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BasicViewEnum.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }

}
