package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum FormBuilderArrowButtonIdentifiers {

    ArrowCollapsedStateExpand("expand"),
    ArrowCollapsedStateDown("down"),
    ArrowExpandedStateCollapse("collapse"),
    ArrowExpandedStateUp("up");

    private final String value;

    FormBuilderArrowButtonIdentifiers(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
