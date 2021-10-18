package com.example.kairo.listoflistsback.framework.exception;

public class WarningException extends RuntimeException {

    public WarningException() {
        super();
    }

    public WarningException(String message) {
        super(message);
    }

    public WarningException(String message, Throwable cause) {
        super(message, cause);
    }

    public WarningException(Throwable cause) {
        super(cause);
    }

    protected WarningException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
