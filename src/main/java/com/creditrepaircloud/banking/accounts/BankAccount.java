package com.creditrepaircloud.banking.accounts;

import com.creditrepaircloud.banking.exceptions.InsufficientFundsException;
import com.creditrepaircloud.banking.exceptions.InvalidInputAmountException;

import java.util.LinkedList;
import java.util.List;

public abstract class BankAccount implements Account {

    private final int accountNumber;
    private double balance;
    protected AccountType accountType;
    private final int customerId;
    private List<Transaction> transactionList = new LinkedList<>();

    private static int accountNumberSequence = 1234;


    public BankAccount(int customerId) {
        this.accountNumber = ++accountNumberSequence;
        this.balance = 0;
        this.customerId = customerId;
    }


    public int getCustomerId() {
        return this.customerId;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    protected void credit(double amount, int refNumber) throws InvalidInputAmountException {
        if (amount <= 0) {
            throw new InvalidInputAmountException(amount);
        }
        this.balance = this.balance + amount;
        Transaction transaction = new Transaction(TransactionType.CREDIT, refNumber, amount);
        transactionList.add(transaction);

    }

    protected void debit(double amount, int refNumber) throws InvalidInputAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidInputAmountException(amount);
        }

        if(this.balance < amount) {
            throw new InsufficientFundsException(amount);
        }

        this.balance = this.balance - amount;
        Transaction transaction = new Transaction(TransactionType.DEBIT, refNumber, amount);
        transactionList.add(transaction);
    }

    public List<Transaction> listTransactions() {
        return  this.transactionList;
    }
}
