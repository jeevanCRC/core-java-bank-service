package com.creditrepaircloud.banking.exceptions;

public class InsufficientFundsException extends Exception {
    private double amount;

    public InsufficientFundsException() {
        super();
    }

    public InsufficientFundsException(double amount) {
        super();
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InsufficientFundsException{" +
                "amount=" + amount +
                '}';
    }
}
