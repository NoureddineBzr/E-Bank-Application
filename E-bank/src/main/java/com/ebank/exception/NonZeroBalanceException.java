package com.ebank.exception;

public class NonZeroBalanceException extends IllegalStateException {
    public NonZeroBalanceException() {
        super("Account balance must be zero before closing");
    }
}
