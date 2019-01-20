package com.nilin.util;

public class CustomErrorType extends RuntimeException {

    private String errorMessage;

    public CustomErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
