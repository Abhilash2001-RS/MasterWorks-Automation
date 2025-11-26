package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum DefaultDistributionCurvesColumnNames {
    Curve_Name("Curve Name"),
    Description("Description"),
    Is_Default("Is Default"),
    Is_System("Is System"),
    Created_On("Created On"),
    Created_By("Created By"),
    Modified_By("Modified By"),
    Modified_On("Modified On");

    private final String value;

    DefaultDistributionCurvesColumnNames(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * This method is used to get the list of distribution curve column
     *
     * @return all distribution curve column
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : DefaultDistributionCurvesColumnNames.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

}
