package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProjectFundingRulesPageColumns {
    RuleID("Rule ID"),
    RuleName("Rule Name"),
    CreatedDate("Created Date"),
    CreatedBy("Created By"),
    IsDefault("IsDefault"),
    IsActive("Is Active"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    // declaring private variable for getting values
    private String value;

    ProjectFundingRulesPageColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ProjectFundingRulesPageColumns items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ProjectFundingRulesPageColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
