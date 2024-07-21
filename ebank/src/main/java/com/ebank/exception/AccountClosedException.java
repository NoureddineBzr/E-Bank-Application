package com.ebank.exception;

public class AccountClosedException extends RuntimeException {
    public AccountClosedException() {
        super("The account is closed");
    }
}
