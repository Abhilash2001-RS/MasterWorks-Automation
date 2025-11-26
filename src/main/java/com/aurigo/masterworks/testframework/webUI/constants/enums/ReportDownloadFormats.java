package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ReportDownloadFormats {

    Excel("Excel"),
    PDF("PDF");


    private String value;

    ReportDownloadFormats(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Report Download Formats
     */
    public static List<ReportDownloadFormats> getList() {
        List<ReportDownloadFormats> listToReturn = new ArrayList<>();
        for (var item : ReportDownloadFormats.values()) {
            listToReturn.add(item);
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
