package com.aurigo.masterworks.testframework.utilities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuiteTree {

    private int id;

    private List<SuiteTree> children;

    private boolean hasChildren;

    private String suiteType;

    private String name;

    private ParentSuite parentSuite;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setChildren(List<SuiteTree> children) {
        this.children = children;
    }

    public List<SuiteTree> getChildren() {
        return this.children;
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

    public ParentSuite getParentSuite(){return this.parentSuite; }

    public void setParentSuite(ParentSuite parentSuite) {
        this.parentSuite = parentSuite;
    }
}
