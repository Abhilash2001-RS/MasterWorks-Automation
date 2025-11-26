package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum RiskRegisterExcelColumns {
    InternalID("InternalID"),
    RecordID("RecordID"),
    Contract("Contract:"),
    Title("Title:"),
    Description("Description:"),
    Category("Category:"),
    Owner("Owner:"),
    Identifier("Identifier:"),
    IdentifiedOn("Identified On:"),
    InitialRiskLikelyHood("Initial Risk Likelihood:"),
    InitialRiskSeverity("Initial Risk Severity:"),
    MitigationPlan("Mitigation Plan:"),
    CurrentRiskLikelyHood("Current Risk Likelihood:"),
    CurrentRiskSeverity("Current Risk Severity:");

    private String value;

    RiskRegisterExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Risk register Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : RiskRegisterExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
