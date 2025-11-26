package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProgramType {

    Regular("Regular"),
    Contingency("Contingency");

    private final String value;

    ProgramType(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enum values
     */
    public static List<String> getList() {
        return Stream.of(ProgramType.values())
                .map(ProgramType::getValue)
                .collect(Collectors.toList());
    }

    public String getValue() {
        return this.value;
    }
}
