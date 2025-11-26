package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProgramSummaryByFundReportHeaders
{
    FundName("Fund Name"),
    ProjectName("Project Name"),
    Allocated("Allocated"),
    Consumed("Consumed"),
    Remaining("Remaining");

    private String value;

    ProgramSummaryByFundReportHeaders(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Program Summary By Fund Report Headers
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ProgramSummaryByFundReportHeaders.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
