package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FormBuilderAdvancedControls {

    MultiSelectDropDrown("MultiSelect Drop Down"),
    MultiPicker("Multi Picker"),
    Link("Link"),
    Set("Set"),
    PickerWithTextbox("Picker With Textbox"),
    Picture("Picture"),
    Button("Button"),
    LinkButton("Link Button"),
    Formula("Formula"),
    Map("Map"),
    GISDataMapper("GISDataMapper"),
    File("File"),
    PickerTrigger("Picker Trigger"),
    PictureTaker("Picture Taker");

    private final String value;

    FormBuilderAdvancedControls(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enum values
     */
    public static List<String> getList() {
        List<String> enumValues = Stream.of(FormBuilderAdvancedControls.values())
                .map(e -> e.getValue())
                .collect(Collectors.toList());
        return enumValues;
    }

    public String getValue() {
        return this.value;
    }
}
