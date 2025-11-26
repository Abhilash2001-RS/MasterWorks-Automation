package com.aurigo.masterworks.testframework.webUI.constants.enums;

public enum DocumentFolderStructureColumn {
    FolderStructureName("Folder structure name"),
    IsDefault("Is Default"),
    CreatedOn("Created On"),
    Description("Description");

    private final String value;

    private DocumentFolderStructureColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
