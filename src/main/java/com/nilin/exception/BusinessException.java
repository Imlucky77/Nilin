package com.nilin.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private String message;
    private int status;

    public BusinessException(int status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
