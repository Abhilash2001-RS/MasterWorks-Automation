package com.aurigo.masterworks.testframework.utilities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("testCase")
    @Expose
    private TestCase testCase;
    @SerializedName("testPoint")
    @Expose
    private TestPoint testPoint;
    @SerializedName("testPlan")
    @Expose
    private TestPlan testPlan;
    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("outcome")
    @Expose
    private String outcome;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("startedDate")
    @Expose
    private String startedDate;

    @SerializedName("completedDate")
    @Expose
    private String completedDate;

    @SerializedName("createdDate")
    @Expose
    private String createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public String getStartedDate() {
        return startedDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public TestPoint getTestPoint() {
        return testPoint;
    }

    public void setTestPoint(TestPoint testPoint) {
        this.testPoint = testPoint;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getComment() {
        return comment;
    }
}