package com.creditrepaircloud.banking.exceptions;

public class InvalidInputAmountException extends Exception {
    private double amount;

    public InvalidInputAmountException() {
        super();
    }

    public InvalidInputAmountException(double amount) {
        super();
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InvalidInputAmountException{" +
                "amount=" + amount +
                '}';
    }
}
