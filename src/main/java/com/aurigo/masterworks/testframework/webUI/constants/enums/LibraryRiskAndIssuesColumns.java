package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * List of texts of the column names in the Library pages related to Risk and Issues
 */
public enum LibraryRiskAndIssuesColumns {

    Area("Area"),
    Description("Description"),
    UnitName("Unit Name"),
    Priority("Priority"),
    Sequence("Sequence"),
    Type("Type"),
    Category("Category"),
    RiskColor("Risk Color"),
    MinRiskScore("Min Risk Score"),
    MaxRiskScore("Max Risk Score"),
    RiskProbability("Risk Probability"),
    ProbabilityRating("Probability Rating"),
    RiskSeverity("Risk Severity"),
    SeverityRating("Severity Rating");

    // declaring private variable for getting values
    private String value;

    LibraryRiskAndIssuesColumns(String value) {
        this.value = value;
    }

    /**
     * method to return the list of the column names
     *
     * @return list of the column names
     */
    public static List<String> getListOfColumnNames() {
        var listToReturn = new ArrayList();
        for (var item : LibraryRiskAndIssuesColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }

}
