package com.creditrepaircloud.banking.accounts;

import com.creditrepaircloud.banking.exceptions.InsufficientFundsException;
import com.creditrepaircloud.banking.exceptions.InvalidInputAmountException;

public class CurrentAccount extends BankAccount{

    public CurrentAccount(int id) {
        super(id);
        this.accountType = AccountType.CURRENT;
    }

    @Override
    public void withdraw(double amount) throws InvalidInputAmountException, InsufficientFundsException {
       this.debit(amount, 0);
    }

    @Override
    public void deposit(double amount) throws InvalidInputAmountException {
       this.credit(amount, 0);
    }

    public void transfer(BankAccount destination, double amount) throws InvalidInputAmountException, InsufficientFundsException {
        this.debit(amount, destination.getAccountNumber());
        destination.credit(amount, this.getAccountNumber());
    }

}
