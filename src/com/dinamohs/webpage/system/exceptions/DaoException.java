package com.dinamohs.webpage.system.exceptions;

public class DaoException extends RuntimeException {

    protected Throwable throwable;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

    @Override
    public Throwable getCause() {
        return throwable;
    }
}
