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
            this.credit(amount);
    }

    @Override
    public void withdraw(double amount) throws InvalidInputAmountException, InsufficientFundsException {
        this.debit(amount);
    }

    @Override
    public void transfer(BankAccount destination, double amount) throws InvalidInputAmountException, InsufficientFundsException {
        this.transferAmount(destination, amount);
    }

}
