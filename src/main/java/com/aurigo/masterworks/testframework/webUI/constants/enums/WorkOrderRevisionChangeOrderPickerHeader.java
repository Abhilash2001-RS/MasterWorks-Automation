package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum WorkOrderRevisionChangeOrderPickerHeader {
    Project("Project"),
    ChangeOrderId("Change Order ID"),
    LineNumber("Line Number"),
    AmountIn$("Amount in $"),
    Quantity("Quantity"),
    ChangeOrderNumber("ChangeOrder Number");
    // declaring private variable for getting values
    private String value;

    WorkOrderRevisionChangeOrderPickerHeader(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of WorkOrderRevisionChangeOrderPickerHeader items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : WorkOrderRevisionChangeOrderPickerHeader.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
