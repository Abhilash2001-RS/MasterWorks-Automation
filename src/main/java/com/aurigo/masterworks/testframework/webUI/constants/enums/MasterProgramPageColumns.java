package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MasterProgramPageColumns {

    Title("Title"),
    Description("Description"),
    ProgramYear("Program Year"),
    CreatedBy("Created By"),
    CreatedOn("Created On"),
    ModifiedOn("Modified On"),
    ModifiedBy("Modified By"),
    PendingOnRoles("Pending On Role(s)"),
    WorkflowStatus("Workflow Status");


    private String value;

    MasterProgramPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Master program page columns
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MasterProgramPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}