package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ProgramListPageHeader {
    Title("Title"),
    Description("Description"),
    ProgramYear("Program Year"),
    CreatedBy("Created By"),
    CreatedOn("Created On"),
    ModifiedBy("Modified By"),
    ModifiedOn("Modified On"),
    WorkflowStatus("Workflow Status"),
    Name("Name"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    private ProgramListPageHeader(String value) {
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
}
