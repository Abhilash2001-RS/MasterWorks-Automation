package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PortfolioListPageHeader {
    PortfolioCode("Portfolio Code"),
    PortfolioManager("PortfolioManager"),
    Description("Description"),
    CreatedOn("Created Date"),
    CreatedBy("Created By");

    private String value;

    private PortfolioListPageHeader(String value) {
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
        for (var item : PortfolioListPageHeader.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }


}
