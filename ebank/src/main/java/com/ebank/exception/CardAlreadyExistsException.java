package com.ebank.exception;

public class CardAlreadyExistsException extends RuntimeException {
    public CardAlreadyExistsException() {
        super("A card of the same type already exists for this account.");
    }
}
