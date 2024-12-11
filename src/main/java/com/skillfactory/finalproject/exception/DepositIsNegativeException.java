package com.skillfactory.finalproject.exception;

public class DepositIsNegativeException extends  RuntimeException {
    public DepositIsNegativeException(String message) {
        super(message);
    }
}
