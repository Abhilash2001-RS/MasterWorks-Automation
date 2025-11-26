package com.aurigo.masterworks.testframework.webUI.constants.enums;
public enum UserRegistration {
    PasswordErrorMessage("passwordFieldErrorMessage"),
    ConfirmPasswordErrorMessage("confirmPasswordFieldErrorMessage"),
    PasswordNotMatchingErrorMessage("passwordNotMatchingErrorMessage"),
    FirstNameErrorMessage("firstNameErrorMessage"),
    EmailErrorMessage("emailErrorMessage"),
    EmailFormatErrorMessage("emailFormatErrorMessage"),
    UserNameErrorMessage("getUserNameErrorMessage"),
    PasswordCriteriaErrorMessage("getPasswordCriteriaNotMatchingErrorMessage");

    private final String value;

    UserRegistration(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
