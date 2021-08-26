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
       this.debit(amount);
    }

    @Override
    public void deposit(double amount) throws InvalidInputAmountException {
       this.credit(amount);
    }

    public void transfer(BankAccount destination, double amount) throws InvalidInputAmountException, InsufficientFundsException {
        this.transferAmount(destination, amount);
    }

}
