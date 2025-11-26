package com.aurigo.masterworks.testframework.utilities.enums;

/**
 * Help to upload S3 bucket
 */
public enum S3UploadHelper {

    URL("Url"),
    FILE_NAME("FileName");

    final String value;

    S3UploadHelper(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
