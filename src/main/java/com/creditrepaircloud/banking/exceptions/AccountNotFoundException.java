package com.creditrepaircloud.banking.exceptions;

public class AccountNotFoundException extends Exception{
    private int accountNumber;

    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(int accountNumber) {
        super();
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "AccountNotFoundException{" +
                "accountNumber=" + accountNumber +
                '}';
    }
}
