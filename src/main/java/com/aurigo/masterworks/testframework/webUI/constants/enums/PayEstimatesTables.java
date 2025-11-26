package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum PayEstimatesTables {

    WorkDone("Work Done"),
    AdvancePayments("Advance Payments"),
    Adjustments("Adjustments"),
    AdvanceRecoveries("Advance Recoveries");

    private final String value;

    PayEstimatesTables(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
