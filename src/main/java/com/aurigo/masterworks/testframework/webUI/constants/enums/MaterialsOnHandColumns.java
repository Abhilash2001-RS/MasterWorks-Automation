package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MaterialsOnHandColumns {
    MOHID("MOH ID"),
    MOHDate("MOH Date"),
    Status("Status"),
    RemainingAmountIn$("Remaining Amount in $"),
    Contractor("Contractor"),
    ApprovedOn("Approved On"),
    PrePaymentAmount("Pre-Payment Amount"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    MaterialsOnHandColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Material on Hands  Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : MaterialsOnHandColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
