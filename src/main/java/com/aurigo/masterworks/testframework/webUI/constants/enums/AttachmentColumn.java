package com.aurigo.masterworks.testframework.webUI.constants.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AttachmentColumn {
    FileViewStatus("File View Status"),
    DocumentName("Document Name"),
    UrlLink("Url/Link"),
    Title("Title"),
    UploadedBy("Uploaded By"),
    UploadedDate("Uploaded Date"),
    FileSize("File Size");

    private final String value;

    AttachmentColumn(String value) {
        this.value = value;
    }

    /**
     * Method to get the list of AttachmentColumn headers from the enum
     *
     * @return list of AttachmentColumn headers
     */
    public static List<String> getList() {
        return Stream.of(AttachmentColumn.values())
                .map(AttachmentColumn::getValue)
                .collect(Collectors.toList());
    }

    public String getValue() {
        return this.value;
    }

}
