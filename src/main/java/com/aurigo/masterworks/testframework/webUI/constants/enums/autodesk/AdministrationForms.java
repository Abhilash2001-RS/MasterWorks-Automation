package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk;

import java.util.ArrayList;
import java.util.List;

public enum AdministrationForms {

    ApplicationSettings("Application Settings"),
    AuthenticationLoginSettings("Authentication & Login Settings"),
    WorkflowSettings("Workflow Settings"),
    FeatureSettings("Feature Settings"),
    PlanningManagement("Planning Management"),
    BudgetManagement("Budget Management"),
    ContractManagement("Contract Management"),
    ReportConfigurations("Report Configurations"),
    BusinessUnit("Business Unit"),
    UserAccounts("User Accounts"),
    Permissions("Permissions"),
    SecurityRoles("Security Roles"),
    UserSettings("User Settings"),
    WorkflowManagement("Workflow Management"),
    FormBuilder("Form Builder");

    // declaring private variable for getting values
    private String value;

    AdministrationForms(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Administration Forms Grid column names
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : AdministrationForms.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
