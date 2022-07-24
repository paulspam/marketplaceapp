package com.intellias.intellistart.marketplaceapp.exception;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable t) {
        super(message, t);
    }
}
