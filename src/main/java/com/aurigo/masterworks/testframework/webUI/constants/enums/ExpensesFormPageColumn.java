package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum ExpensesFormPageColumn {
    ExpenseNumber("Expense Number"),
    ExternalRefNumber("External Ref Number"),
    PurchaseOrderNo("Purchase Order No"),
    ExpenseDate("Expense Date"),
    AccountingCode("Accounting Code"),
    VoucherID("Voucher ID"),
    ExpenseDetails("Expense Details"),
    ExpensesType("Expenses Type"),
    Costin$("Cost in $"),
    WorkflowStatus("Workflow Status"),
    PendingOnRole("Pending On Role(s)");

    // declaring private variable for getting values
    private String value;

    ExpensesFormPageColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of ExpensesFormPageColumn items
     */
    public static List<String> getList() {
        var listToReturn = new ArrayList();
        for (var item : ExpensesFormPageColumn.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
