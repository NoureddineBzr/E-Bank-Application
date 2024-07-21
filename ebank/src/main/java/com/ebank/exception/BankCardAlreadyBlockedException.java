package com.ebank.exception;

public class BankCardAlreadyBlockedException extends RuntimeException {
    public BankCardAlreadyBlockedException() {
        super("Bank card is already blocked");
    }
}
