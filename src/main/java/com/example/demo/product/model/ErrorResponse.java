package com.example.demo.product.model;

import lombok.Getter;

@Getter
public class ErrorResponse {
    // Can technically make tour error message as big as you want with more properties
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
