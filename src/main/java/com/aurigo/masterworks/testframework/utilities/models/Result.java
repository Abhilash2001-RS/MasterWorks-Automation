package com.aurigo.masterworks.testframework.utilities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    public String outcome;
    public int count;
}
