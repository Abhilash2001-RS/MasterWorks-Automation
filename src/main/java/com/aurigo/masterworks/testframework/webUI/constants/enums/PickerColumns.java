package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum PickerColumns {

    Area("Area"),
    PackageTitle("Package Title"),
    PackageID("Package ID"),
    Contractor("Contractor"),
    SubmittalID("Submittal ID"),
    ResourceTitleID("Title ID"),
    Phase("Phase"),
    Name("Name"),
    VendorName("Vendor Name"),
    VendorCategory("Vendor Category"),
    VendorSubCategory("Vendor Sub-Category"),
    WorkSize("Work Size"),
    Title("Title"),
    Message("Message");

    private String value;

    PickerColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}