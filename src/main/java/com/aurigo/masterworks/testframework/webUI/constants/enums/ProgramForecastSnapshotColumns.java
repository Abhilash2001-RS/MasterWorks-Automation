package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ProgramForecastSnapshotColumns {
    SnapshotID("Snapshot ID"),
    SnapshotDescription("Snapshot Description"),
    CreatedBy("Created By"),
    CreatedOn("Created On");

    private String value;

    private ProgramForecastSnapshotColumns(String value) {
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
