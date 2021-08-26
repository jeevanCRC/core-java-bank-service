package com.creditrepaircloud.banking.accounts;

import com.creditrepaircloud.banking.exceptions.InsufficientFundsException;
import com.creditrepaircloud.banking.exceptions.InvalidInputAmountException;

public interface Account {
    public void deposit(double amount) throws InvalidInputAmountException;
    public void withdraw(double amount) throws InvalidInputAmountException, InsufficientFundsException;
    public void transfer(BankAccount account, double amount) throws InvalidInputAmountException, InsufficientFundsException;
}
