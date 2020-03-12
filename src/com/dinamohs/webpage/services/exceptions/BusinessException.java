package com.dinamohs.webpage.services.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message, Exception ex) {
        super(message, ex);
    }
}
