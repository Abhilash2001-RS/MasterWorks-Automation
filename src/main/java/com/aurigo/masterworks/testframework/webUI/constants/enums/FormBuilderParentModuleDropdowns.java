package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public enum FormBuilderParentModuleDropdowns {

    Administration("Administration"),
    BidEstimateInformation("Bid Estimate Information"),
    Budget("Budget"),
    Contracts("Contracts"),
    Enterprise("Enterprise"),
    Land("Land"),
    Library("Library"),
    MasterContracts("Master Contracts"),
    Planning("Planning"),
    Project("Project"),
    WorkOrders("Work Orders"),
    WorkOrdersAndContracts("Work Orders and Contracts"),
    Settings("Settings");

    private final String value;

    FormBuilderParentModuleDropdowns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enum values
     */
    public static List<String> getList() {
        return Stream.of(FormBuilderParentModuleDropdowns.values())
                .map(FormBuilderParentModuleDropdowns::getValue)
                .collect(Collectors.toList());
    }

    public String getValue() {
        return this.value;
    }
}
