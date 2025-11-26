package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum MasterContractCodePickerColumnHeaders {
    MasterContractCode("Master Contract Code"),
    MasterContractName("Master Contract Name"),
    AwardedConsultant("Awarded Consultant"),
    StartDate("Start Date"),
    EndDate("End Date"),
    MeasurementSystem("Measurement System");

    private final String value;

    MasterContractCodePickerColumnHeaders(String value) {
        this.value = value;
    }

    /**
     * This method is used to get the value of the MasterContract Code Picker Column Headers
     *
     * @return column value
     */
    public String getValue() {
        return value;
    }
}
