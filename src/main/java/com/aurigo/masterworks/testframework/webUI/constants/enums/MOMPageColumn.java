package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MOMPageColumn {
    MeetingID("Meeting ID"),
    MeetingType("Meeting Type"),
    Series("Series"),
    Subject("Subject"),
    CreatedBy("Created By"),
    MeetingDate("Meeting Date"),
    Contract("Contract"),
    WorkflowStatus("Workflow Status"),
    PendingOnRole("Pending On Role(s)"),
    OrganizerName("Organizer Name");

    // declaring private variable for getting values
    private String value;

    MOMPageColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of MOMPageColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MOMPageColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
