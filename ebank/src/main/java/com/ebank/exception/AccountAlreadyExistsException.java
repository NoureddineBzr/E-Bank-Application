package com.ebank.exception;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException() {
        super("An account of this type already exists for this user.");
    }
}
