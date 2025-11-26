package com.aurigo.masterworks.testframework.utilities.enums;

public enum Product {
    // This will call enum constructor with one
    // String argument
    WebUI("WebUI"),
    API("API"),
    Mobile("Mobile"),
    Architecture("Architecture");

    // declaring private variable for getting values
    private String product;

    // enum constructor - cannot be public or protected
    Product(String product) {
        this.product = product;
    }

    // getter method
    public String getProduct() {
        return this.product;
    }
}
