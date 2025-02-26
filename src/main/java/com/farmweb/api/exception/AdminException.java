package com.farmweb.api.exception;

public class AdminException extends RuntimeException {
    public AdminException() {
    }
    public AdminException(String message) {
        super(message);
    }
}
