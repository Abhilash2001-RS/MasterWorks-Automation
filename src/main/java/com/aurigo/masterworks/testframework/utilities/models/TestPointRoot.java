package com.aurigo.masterworks.testframework.utilities.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestPointRoot {

    @SerializedName("value")
    @Expose
    private List<TestPoint> value = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<TestPoint> getValue() {
        return value;
    }

    public void setValue(List<TestPoint> value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}