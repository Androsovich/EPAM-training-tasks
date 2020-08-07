package com.epam.winter.java.lab.exceptions;

public class CalculateException extends RuntimeException {
    public CalculateException() {
    }

    public CalculateException(String message) {
        super(message);
    }

    public CalculateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculateException(Throwable cause) {
        super(cause);
    }

    public CalculateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
