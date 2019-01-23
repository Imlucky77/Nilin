package com.nilin.util;

public class CustomErrorType extends RuntimeException {

    private final String errorMessage;

    public CustomErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
