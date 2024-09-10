package com.example.demo.exceptions;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product Not Found");
    // can add to this list over time
    // messages all in one place

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
