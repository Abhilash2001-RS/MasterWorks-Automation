package com.aurigo.masterworks.testframework.webUI.constants.enums;


public enum RiskAndIssuesNavigationPaths {

    riskCategoryInLibraryModule("Risks & Issues/Risk Category"),
    riskRegisterPathInLeftPaneTree("Risks & Issues/Risk Register"),
    issueLogPath("Risks & Issues/Issue Log"),
    impactAreasInLibraryModule("Risks & Issues/Impact Areas"),
    issueTypeInLibraryModule("Risks & Issues/Issue Type"),
    issuePriorityInLibraryModule("Risks & Issues/Issue Priority"),
    riskProbabilityInLibraryModule("Risks & Issues/Risk Probability"),
    riskGradeInLibraryModule("Risks & Issues/Risk Grade"),
    riskSeverityInLibraryModule("Risks & Issues/Risk Severity");


    private String value;

    RiskAndIssuesNavigationPaths(String value) {
        this.value = value;
    }

    /**
     * Returns the specified string
     *
     * @return the specified string
     */
    public String getValue() {
        return this.value;
    }
}
