package com.ebank.exception;

public class BlockReasonEmptyException extends RuntimeException {
    public BlockReasonEmptyException() {
        super("Block reason cannot be empty.");
    }
}
