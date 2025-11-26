package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MasterContractsColumns {
    ContractCode("Code"),
    Name("Name"),
    AwardedConsultant("Awarded Consultant"),
    EndDate("End Date"),
    RemainingAmountIn$("Remaining Amount in $"),
    WorkflowStatus("Workflow Status"),
    PendingOnRoles("Pending On Role(s)");

    private String value;

    private MasterContractsColumns(String value) {
        this.value = value;
    }

    /**
     * method to return the list of the column names
     *
     * @return list of the column names
     */
    public static List<String> getListOfColumnNames() {
        var listToReturn = new ArrayList();
        for (var item : MasterContractsColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of MasterContractsColumns items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MasterContractsColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }
}
