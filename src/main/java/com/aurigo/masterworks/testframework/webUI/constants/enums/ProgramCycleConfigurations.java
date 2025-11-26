package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProgramCycleConfigurations {
    Name("Name"),
    FiscalStart("Fiscal Start"),
    FiscalEnd("FiscalEnd"),
    ProgramDuration("Program Duration (Yearly)");

    private final String value;

    ProgramCycleConfigurations(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enum values
     */
    public static List<String> getList() {
        return Stream.of(ProgramCycleConfigurations.values())
                .map(ProgramCycleConfigurations::getValue)
                .collect(Collectors.toList());
    }

    public String getValue() {
        return this.value;
    }
}
