package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PortfolioGridHeaders {
    ProjectName("Project Name"),
    ProgramTitle("Title"),
    PortfolioCode("Portfolio Code");

    private String value;

    private PortfolioGridHeaders(String value) {
        this.value = value;
    }

    /**
     * Get enum value
     *
     * @return enum value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Portfolio List Page Header Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PortfolioGridHeaders.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
