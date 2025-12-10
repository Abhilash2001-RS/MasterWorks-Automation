package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum PlannedProjectListPageHeader {

    ProjectName("Project Name"),
    ProjectCode("Project Code"),
    ProgramFamilyCategory("Program Family Category"),
    Owner("Owner"),
    ProjectType("Project Type"),
    IsActive("IsActive"),
    TotalScore("Project Score"),
    Status("Status"),
    DateCreated("Date Created"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    private PlannedProjectListPageHeader(String value) {
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
     * @return list of Planned Project list page Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : PlannedProjectListPageHeader.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
