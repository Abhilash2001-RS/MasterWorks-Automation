package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk.firstLevelNavigation;

import java.util.ArrayList;
import java.util.List;

public enum ProgramModule {
    Program("Program"),
    ReviseProgram("Revise Program");

    private String value;

    private ProgramModule(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Program Module Page Columns.
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : ProgramModule.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}