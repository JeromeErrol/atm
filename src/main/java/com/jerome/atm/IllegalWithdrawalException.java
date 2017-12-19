package com.jerome.atm;

public class IllegalWithdrawalException extends RuntimeException {
    public IllegalWithdrawalException(String message) {
        super(message);
    }
}
