package com.example.kairo.listoflistsback.framework.exception;

public class InfoException extends RuntimeException {

    public InfoException() {
        super();
    }

    public InfoException(String message) {
        super(message);
    }

    public InfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfoException(Throwable cause) {
        super(cause);
    }

    protected InfoException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
