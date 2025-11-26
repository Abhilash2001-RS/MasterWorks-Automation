package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum PermissionModulesInUserManagement {
    Administration("Administration"),
    FormBuilder("Form Builder"),
    FunctionalConfigurations("Functional Configurations"),
    BudgetManagement("Budget Management"),
    ContractManagement("Contract Management"),
    PlanningManagement("Planning Management"),
    ResourceManagement("Resource Management"),
    UserManagement("User Management"),
    DocumentManagement("Document Management");

    private String value;

    PermissionModulesInUserManagement(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
