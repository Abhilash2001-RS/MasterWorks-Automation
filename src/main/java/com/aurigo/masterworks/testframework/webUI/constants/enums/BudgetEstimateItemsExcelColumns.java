package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum BudgetEstimateItemsExcelColumns {
    InternalID("Internal ID"),
    Type("Type"),
    IndentLevel("Indent Level"),
    LineNo("Line No"),
    Name("Name"),
    Description("Description"),
    Notes("Notes"),
    PayItemNo("Pay Item No"),
    UnitPriceInDollars("Unit Price in $"),
    FundRule("Fund Rule"),
    Quantity("Quantity"),
    Unit("Unit"),
    EffectiveFrom("Effective From"),
    InflationRule("Inflation Rule"),
    AnnualInflationRate("Annual Inflation Rate(in %)"),
    AccountingCode("Accounting Code"),
    TitleID("Title ID"),
    ResourceTitle("Resource Title"),
    ResourceType("Resource Type"),
    ResourceUnits("Resource Units"),
    ResourceFixedPriceInDollars("Resource Fixed Price in $"),
    ResourceVariablePriceInDollarsPerUnit("Resource Variable Price in $/Unit"),
    NumberOfResources("Number of Resources"),
    ResourceQuantityInUnits("Resource Quantity in Units"),
    ResourceAmountInDollars("Resource Amount in $");

    private String value;

    BudgetEstimateItemsExcelColumns(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Budget Estimate Item Excel Columns
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : BudgetEstimateItemsExcelColumns.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
