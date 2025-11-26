package com.aurigo.masterworks.testframework.utilities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Suite {

    private int id;

    private boolean hasChildren;

    private String suiteType;

    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean getHasChildren() {
        return this.hasChildren;
    }

    public void setSuiteType(String suiteType) {
        this.suiteType = suiteType;
    }

    public String getSuiteType() {
        return this.suiteType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
