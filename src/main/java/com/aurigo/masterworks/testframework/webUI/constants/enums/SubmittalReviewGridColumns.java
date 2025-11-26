package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum SubmittalReviewGridColumns {
    From("From"),
    To("To"),
    Message("Message"),
    DueDate("Due Date"),
    Status("Status"),
    Response("Response"),
    ResponseDate("Response Date"),
    Contractor("Contractor");

    private String value;

    SubmittalReviewGridColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
