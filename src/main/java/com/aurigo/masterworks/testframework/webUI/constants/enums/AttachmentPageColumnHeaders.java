package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum AttachmentPageColumnHeaders {
    DocumentName("Document Name"),
    Title("Title"),
    Type("Type"),
    Size("Size"),
    CreatedBy("Created By"),
    Version("Version"),
    CheckoutTo("Check out To");

    private String value;

    AttachmentPageColumnHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
