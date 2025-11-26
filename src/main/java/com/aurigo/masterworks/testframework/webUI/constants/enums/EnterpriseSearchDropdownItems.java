package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum EnterpriseSearchDropdownItems {
    NoFilter("NoFilter"),
    Contains("Contains"),
    DoesNotContain("DoesNotContain"),
    StartsWith("StartsWith"),
    EndsWith("EndsWith"),
    EqualTo("EqualTo"),
    NotEqualTo("NotEqualTo"),
    GreaterThan("GreaterThan"),
    LessThan("LessThan"),
    GreaterThanOrEqualTo("GreaterThanOrEqualTo"),
    LessThanOrEqualTo("LessThanOrEqualTo"),
    Between("Between"),
    NotBetween("NotBetween"),
    IsNull("IsNull"),
    NotIsNull("NotIsNull"),
    IsEmpty("IsEmpty"),
    NotIsEmpty("NotIsEmpty"),
    ClearAllFilter("ClearAllFilter");

    // declaring private variable for getting values
    private String value;

    private EnterpriseSearchDropdownItems(String value) {
        this.value = value;
    }

    // getter method
    public String getValue() {
        return this.value;
    }
}
