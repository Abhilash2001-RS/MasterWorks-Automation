package com.aurigo.masterworks.testframework.webUI.constants.enums.autodesk;

import java.util.ArrayList;
import java.util.List;

public enum CapitalForms {

    Home("Home"),
    Program("Program"),
    MasterProgram("Master Program"),
    GlobalFundManagement("Global Fund Management"),
    Reports("Reports"),
    Library("Library"),
    Administration("Administration");

    // declaring private variable for getting values
    public String value;

    CapitalForms(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of CapitalForms Grid column names
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : CapitalForms.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
