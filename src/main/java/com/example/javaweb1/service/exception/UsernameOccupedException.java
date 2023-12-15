package com.example.javaweb1.service.exception;

public class UsernameOccupedException extends ServiceException{
    public UsernameOccupedException() {
        super();
    }

    public UsernameOccupedException(String message) {
        super(message);
    }

    public UsernameOccupedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameOccupedException(Throwable cause) {
        super(cause);
    }

    protected UsernameOccupedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
