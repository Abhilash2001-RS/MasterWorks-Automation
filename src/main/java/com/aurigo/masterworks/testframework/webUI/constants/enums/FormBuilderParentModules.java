package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FormBuilderParentModules {

    Administration("Administration"),
    BidEstimateInformation("Bid Estimate Information"),
    Budget("Budget"),
    Contract("Contracts"),
    Enterprise("Enterprise"),
    Land("Land"),
    Library("Library"),
    MasterContracts("Master Contracts"),
    Planning("Planning"),
    Project ("Project"),
    WorkOrders("Work Orders"),
    WorkOrdersandContracts("Work Orders and Contracts");

    private final String value;

    FormBuilderParentModules(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Enum values
     */
    public static List<String> getList() {
        return Stream.of(FormBuilderParentModules.values())
                .map(FormBuilderParentModules::getValue)
                .collect(Collectors.toList());
    }

    public String getValue() {
        return this.value;
    }
}
