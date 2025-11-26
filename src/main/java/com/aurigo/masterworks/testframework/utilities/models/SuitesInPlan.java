package com.aurigo.masterworks.testframework.utilities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuitesInPlan
{
    private List<Suite> value;

    private int count;

    public List<Suite> getValue(){
        return this.value;
    }
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
}
