package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FormBuilderChildDataContainersSimpleMode {

    DynamicGrid("Dynamic Grid"),
    Picker("Picker");

    private final String value;

    FormBuilderChildDataContainersSimpleMode(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enum values
     */
    public static List<String> getList() {
        List<String> enumValues = Stream.of(FormBuilderChildDataContainersSimpleMode.values())
                .map(e -> e.getValue())
                .collect(Collectors.toList());
        return enumValues;
    }

    public String getValue() {
        return this.value;
    }
}
