package com.aurigo.masterworks.testframework.utilities.enums;

public enum RunType {
    Smoke("Smoke"),
    Sanity("Sanity"),
    Regression("Regression"),
    ScratchBuildSetup("Scratch Build Setup"),
    LibraryDataUpload("Library Data Upload");

    private String value;

    RunType(String runType){
        this.value=runType;
    }

    public String getValue(){
        return this.value;
    }
}
