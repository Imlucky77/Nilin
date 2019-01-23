package com.nilin.util;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String errorMessage;
    private final int status;

    public BusinessException(int status, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
