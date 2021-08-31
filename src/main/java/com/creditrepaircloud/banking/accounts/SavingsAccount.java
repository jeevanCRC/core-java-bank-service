package com.creditrepaircloud.banking.accounts;

import com.creditrepaircloud.banking.exceptions.InsufficientFundsException;
import com.creditrepaircloud.banking.exceptions.InvalidInputAmountException;

public class SavingsAccount extends BankAccount{

    public SavingsAccount(int id) {
        super(id);
        this.accountType = AccountType.SAVINGS;
    }

    @Override
    public void deposit(double amount) throws InvalidInputAmountException {
            this.credit(amount, 0);
    }

    @Override
    public void withdraw(double amount) throws InvalidInputAmountException, InsufficientFundsException {
        this.debit(amount, 0);
    }

    @Override
    public void transfer(BankAccount destination, double amount) throws InvalidInputAmountException, InsufficientFundsException {
        this.debit(amount, destination.getAccountNumber());
        destination.credit(amount, this.getAccountNumber());
    }

}
