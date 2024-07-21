package com.ebank.exception;

public class CardNotAllowedForSavingsException extends RuntimeException {
    public CardNotAllowedForSavingsException() {
        super("Credit cards are not allowed for savings accounts");
    }
}
