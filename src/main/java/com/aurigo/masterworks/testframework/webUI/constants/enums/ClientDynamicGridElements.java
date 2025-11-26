package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ClientDynamicGridElements {
    TextBox("textBox"),
    TextArea("textArea"),
    MultiSelect("multiSelect"),
    DropDown("dropDown"),
    NumericBox("numericBox"),
    CheckBox("checkBox"),
    DropdownTree("dropdownTree"),
    RadioButton("radioButton");

    private String value;

    ClientDynamicGridElements(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
