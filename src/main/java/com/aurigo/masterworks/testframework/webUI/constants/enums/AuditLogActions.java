package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.ArrayList;
import java.util.List;

public enum AuditLogActions {

    Delete("Delete"),
    Insert("Insert"),
    Update("Update"),
    CheckAll("Check All"),
    DocumentNew("Document New"),
    DocumentDelete("Document Delete"),
    Move("Move"),
    CheckIn("Check In"),
    CheckOut("Check Out"),
    Archive("Archive"),
    Docusign("Docusign"),
    GetDocument("Get Document"),
    DocumentPropertyAdd("Document Property Add"),
    DocumentPropertyEdit("Document Property Edit"),
    DocumentPropertyDelete("Document Property Delete"),
    CommentAdd("Comment Add"),
    CommentEdit("Comment Edit"),
    CommentDelete("Comment Delete"),
    AnnotationsAdd("Annotations Add"),
    AnnotationsEdit("Annotations Edit"),
    AnnotationsDelete("Annotations Delete"),
    AnnotationsConsolidated("Annotations Consolidated"),
    AnnotationsConsolidatedEdited("Annotations Consolidation Edited"),
    AnnotationsConsolidatedRemoved("Annotations Consolidation Removed"),
    FolderAdd("Folder Add"),
    FolderEdit("Folder Edit"),
    FolderDelete("Folder Delete"),
    Permission("Permission");

    private String value;

    AuditLogActions(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of items from the enum
     *
     * @return list of Audit log Actions
     */
    public static List<String> getList() {
        List<String> listToReturn = new ArrayList<>();
        for (var item : AuditLogActions.values()) {
            listToReturn.add(item.getValue());
        }
        return listToReturn;
    }

    public String getValue() {
        return this.value;
    }
}
