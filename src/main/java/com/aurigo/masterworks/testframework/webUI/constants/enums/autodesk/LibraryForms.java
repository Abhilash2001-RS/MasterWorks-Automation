package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk;

import java.util.ArrayList;
import java.util.List;

public enum LibraryForms {

    MailBodyTemplates("Mail Body Templates"),
    BudgetEstimateType("Budget Estimate Type"),
    BudgetTemplate("Budget Template"),
    StandardItemsTable("Standard Items Table"),
    StrategicGoals("Strategic Goals"),
    StrategicObjectives("Strategic Objectives"),
    FundAgency("Fund Agency"),
    FundCategories("Fund Categories"),
    FundType("Fund Type"),
    Materials("Materials"),
    Contractors("Contractors"),
    ContractType("Contract Type"),
    ContractorType("Contractor Type"),
    PurchaseOrderType("Purchase Order Type"),
    ProgramCategory("Program Category"),
    Calendar("Calendar"),
    ProjectType("Project Type"),
    ScoringCategory("Scoring Category"),
    ScoringCriteria("Scoring Criteria"),
    ScoringDepartment("Scoring Department"),
    MeasurementSystems("Measurement Systems"),
    ProjectPhase("Project Phase");

    // declaring private variable for getting values
    private String value;

    LibraryForms(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Library Forms Grid column names
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : LibraryForms.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
