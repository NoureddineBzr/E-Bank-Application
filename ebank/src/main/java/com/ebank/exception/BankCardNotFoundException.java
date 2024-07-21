package com.ebank.exception;

public class BankCardNotFoundException extends RuntimeException {
    public BankCardNotFoundException() {
        super("Bank card not found");
    }
}
