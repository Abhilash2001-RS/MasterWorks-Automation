package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ConsolidatedVendorScoringGridColumns {

    EvaluationCategory("Evaluation Category"),
    Criterion("Criterion"),
    MaxScore("Max Score"),
    CategoryAvgScore("Category Avg. Score"),
    Comments("Comments");

    private String value;

    ConsolidatedVendorScoringGridColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
