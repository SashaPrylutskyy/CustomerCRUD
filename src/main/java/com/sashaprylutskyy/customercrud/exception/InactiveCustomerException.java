package com.sashaprylutskyy.customercrud.exception;

public class InactiveCustomerException extends RuntimeException {
    public InactiveCustomerException(String message) {
        super(message);
    }
}
