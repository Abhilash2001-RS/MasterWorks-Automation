package com.aurigo.masterworks.testframework.webUI.constants.enums;

/**
 * Actions in item list page
 */
public enum ItemListPageActions {

    CopyItem("copy item"),
    PasteItem("paste item"),
    SubItem("sub item"),
    CopySubItem("copy sub item");

    // declaring private variable for getting values
    private String value;

    ItemListPageActions(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
