package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProgramGrids {

    Programs("Programs"),
    Projects("Projects"),
    ProgramBudget("Program Budget");

    private final String value;

    ProgramGrids(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enum values
     */
    public static List<String> getList() {
        return Stream.of(ProgramGrids.values())
                .map(ProgramGrids::getValue)
                .collect(Collectors.toList());
    }

    public String getValue() {
        return this.value;
    }
}
