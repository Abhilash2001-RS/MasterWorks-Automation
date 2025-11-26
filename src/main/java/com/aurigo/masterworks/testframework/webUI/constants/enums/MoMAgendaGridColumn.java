package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MoMAgendaGridColumn {
    StartTime("Start Time"),
    EndTime("End Time"),
    Agenda("Agenda"),
    Owner("Owner");

    // declaring private variable for getting values
    private String value;

    MoMAgendaGridColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of MoMAgendaGridColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MoMAgendaGridColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
