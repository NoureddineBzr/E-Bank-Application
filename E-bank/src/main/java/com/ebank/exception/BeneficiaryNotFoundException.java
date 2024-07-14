package com.ebank.exception;

public class BeneficiaryNotFoundException extends RuntimeException {
    public BeneficiaryNotFoundException() {
        super("Beneficiary not found");
    }
}
