package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum TimeZoneList {
    UTC("+0:00"),
    IndiaStandardTime("+05:30"),
    LineIslandsStandardTime("+14:00"),
    EasternStandardTime("-05:00"),
    CentralAmericaStandardTime("-06:00"),
    CentralStandardTime("-06:00");


    private String value;

    TimeZoneList(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }


}
