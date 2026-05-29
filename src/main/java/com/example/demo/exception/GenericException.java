package com.example.demo.exception;

public class GenericException extends RuntimeException {
    private final int errorCode;

    public GenericException(String message,int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
