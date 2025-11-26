package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum VendorScoringGridColumns {
    EvaluationCategory("Evaluation Category"),
    Criterion("Criterion"),
    MaxScore("Max Score"),
    MyScore("My Score"),
    Comments("Comments"),
    NormalizedScore("Normalized Score");

    private String value;

    VendorScoringGridColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
