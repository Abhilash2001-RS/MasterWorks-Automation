package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum PickerFilterOptions {
    Contains("Contains"),
    DoesNotContain("Does Not Contain"),
    StartsWith("Starts With"),
    EndsWith("Ends With"),
    IsEqualTo("Is Equal To"),
    IsNotEqualTo("Is Not Equal To"),
    IsEmpty("Is empty"),
    NotIsEmpty("Is not empty"),
    IsNull("Is Null"),
    IsNotNull("Is Not Null"),
    IsGreaterThan("Is greater than"),
    Isequalto("Is equal to"); // This is a bug and TODO - Product Backlog Item 480820: Program - Forecast Details-> Enhance Forecast Details for a contingency program & other fixes/enhancements

    // declaring private variable for getting values
    private String value;

    private PickerFilterOptions(String value) {
        this.value = value;
    }

    // getter method
    public String getValue() {
        return this.value;
    }
}
