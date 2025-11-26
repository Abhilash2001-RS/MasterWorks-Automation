package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum ContractChangeOrderListPageColumns {

    ShortDescription("Short Description"),
    ChangeOrderNumber("Change Order Number"),
    AmountIn$("Amount in $");

    private String value;

    public String getValue() {
        return value;
    }

    private ContractChangeOrderListPageColumns(String value) {
        this.value = value;
    }
}
