package com.es.phoneshop.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException() {
    }
}
