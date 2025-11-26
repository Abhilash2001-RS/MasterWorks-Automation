package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FormBuilderGroupLayoutSimpleMode {

    Section("Section"),
    Spacer("Spacer"),
    TabsContainer("Tabs Container"),
    Tab("Tab");

    private final String value;

    FormBuilderGroupLayoutSimpleMode(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enum values
     */
    public static List<String> getList() {
        List<String> enumValues = Stream.of(FormBuilderGroupLayoutSimpleMode.values())
                .map(e -> e.getValue())
                .collect(Collectors.toList());
        return enumValues;
    }

    public String getValue() {
        return this.value;
    }
}
