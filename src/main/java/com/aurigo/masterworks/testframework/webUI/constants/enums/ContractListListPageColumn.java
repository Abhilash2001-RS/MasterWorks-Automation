package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ContractListListPageColumn {
    ContractName("Contract Name"),
    ContractID("Contractor ID"),
    Contractor("Contractor"),
    PrimeContractor("Prime Contractor"),
    Status("Status"),
    WorkFlowStatus("Workflow Status");

    private final String value;

    ContractListListPageColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
