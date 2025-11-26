package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ProgramCategory {

    Central(1),
    East(2),
    West(3),
    North(4),
    South(5);

    private final int value;

    ProgramCategory(int value) {
        this.value = value;
    }

    /**
     * Method to return the value of a Program Category
     *
     * @return value of the program category
     */
    public int getValue() {
        return this.value;
    }
}
