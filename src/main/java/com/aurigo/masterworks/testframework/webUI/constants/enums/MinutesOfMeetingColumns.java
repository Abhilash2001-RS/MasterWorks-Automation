package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MinutesOfMeetingColumns {
    MeetingID("Meeting ID"),
    InternalID("InternalID"),
    RecordID("RecordID"),
    Contract("Contract:"),
    MeetingType("Meeting Type:"),
    MeetingDate("Meeting Date:"),
    Series("Series:"),
    Organizer("Organizer:"),
    MeetingStartTime("Meeting Start Time:"),
    MeetingEndTime("Meeting End Time:"),
    VenueId("VenueId"),
    VenueOthers("Venue Others"),
    Subject("Subject:"),
    ExtendAgendasToAllOccurrencesOfTheSeries("Extend agendas to all occurrences of the series"),
    MeetingNotes("MeetingNotes"),
    NextMeetingDateAndTime("Next Meeting Date & Time:"),
    NextMeetingVenueId("NextMeetingVenueId"),
    NextMeetingVenueOthers("Next Meeting Venue Others"),
    NextMeetingNotes("NextMeetingNotes"),
    StartTime("Start Time"),
    EndTime("End Time"),
    Agenda("Agenda"),
    Owner("Owner"),
    UserName("User Name"),
    FirstName("First Name"),
    LastName("Last Name"),
    Email("Email"),
    Department("Department"),
    Attendance("Attendance"),
    SubjectLabel("Subject");


    // declaring private variable for getting values
    private String value;

    MinutesOfMeetingColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Minutes of Meeting items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MinutesOfMeetingColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
