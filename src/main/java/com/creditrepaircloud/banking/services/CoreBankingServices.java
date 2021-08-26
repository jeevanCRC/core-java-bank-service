package com.creditrepaircloud.banking.services;

import com.creditrepaircloud.banking.accounts.BankAccount;
import com.creditrepaircloud.banking.exceptions.InsufficientFundsException;
import com.creditrepaircloud.banking.exceptions.InvalidInputAmountException;

public interface CoreBankingServices {
    void transferAmount(BankAccount fromAccount, BankAccount toAccount, double amount) throws InvalidInputAmountException, InsufficientFundsException;
}
