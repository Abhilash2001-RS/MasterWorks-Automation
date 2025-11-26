package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FormBuilderGeneralTabLabels {

    FormID("Form ID"),
    Header("Header"),
    ParentModule("Parent Module"),
    Folder("Folder");

    private final String value;

    FormBuilderGeneralTabLabels(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enum values
     */
    public static List<String> getList() {
        List<String> enumValues = Stream.of(FormBuilderGeneralTabLabels.values())
                .map(e -> e.getValue())
                .collect(Collectors.toList());
        return enumValues;
    }

    public String getValue() {
        return this.value;
    }
}
