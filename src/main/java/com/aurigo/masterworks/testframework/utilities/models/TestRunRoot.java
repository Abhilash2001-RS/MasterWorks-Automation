package com.aurigo.masterworks.testframework.utilities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestRunRoot {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("value")
    @Expose
    private List<TestResult> value = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<TestResult> getValue() {
        return value;
    }

    public void setValue(List<TestResult> value) {
        this.value = value;
    }

}