package com.dinamohs.webpage.system.exceptions;

public class UserDaoException extends DaoException {

    public UserDaoException(String message) {
        super(message);
    }

    public UserDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
