package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum DPRImportHeaders {
        InternalID("InternalID"),
        RecordID("RecordID"),
        Contractor("Contractor :"),
        Inspector("Inspector :"),
        Location("Location :"),
        DPRDate("DPR Date :"),
        LowTemperature("Low Temperature :"),
        Skies("Skies :"),
        Precipitation("Precipitation :"),
        WorkingConditions("Working Conditions :"),
        HighTemperature("High Temperature :"),
        Wind("Wind :"),
        Soil("Soil :"),
        Notes("Notes :"),
        InPlace("In Place :"),
        NeedsMaintenance("Needs Maintenance :"),
        MaintenanceNotes("Maintenance Notes"),
        MaterialTestingNotes("Material Testing Notes"),
        Comments("Comments");

        private String value;

        DPRImportHeaders(String value) {
            this.value = value;
        }

    public String getValue() {
        return this.value;
    }

    public static List<String> getList(){
            var listToReturn = new ArrayList();
            for(var name:DPRImportHeaders.values()){
                listToReturn.add(name.getValue());
            }
            return listToReturn;
    }
}
