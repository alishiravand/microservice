package com.rastech.customer.exception;

/**
 * @author Ali Shiravand, 10/6/22 12:44 PM
 */
public class FraudsterException extends Exception {
    public FraudsterException() {
    }

    public FraudsterException(String message) {
        super(message);
    }

    public FraudsterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FraudsterException(Throwable cause) {
        super(cause);
    }

    public FraudsterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
