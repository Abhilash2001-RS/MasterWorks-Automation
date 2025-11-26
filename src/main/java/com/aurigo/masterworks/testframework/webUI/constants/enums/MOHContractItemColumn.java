package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum MOHContractItemColumn {
    LineNo("Line No."),
    Description("Description"),
    AmountIn$("Amount in $"),
    RecoveryQuantityRange("Recovery Quantity Range"),
    RecoveryQuantity("Recovery Quantity"),
    RecoveryRate("Recovery Rate in $"),
    RecoveredQuantity("Recovered Quantity"),
    RecoveredAmount("Recovered Amount"),
    IsComplete("Is Complete"),
    PayItemNo("Pay Item No.");

    // declaring private variable for getting values
    private String value;

    MOHContractItemColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of MOHContractItemColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : MOHContractItemColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
