package com.nilin.util;

import lombok.Data;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private String errorMessage;
    private int status;

    public BusinessException(int status, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
