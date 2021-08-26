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
    static List<Transaction> transactionList = new LinkedList<>();

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

    protected void credit(double amount) throws InvalidInputAmountException {
        if (amount <= 0) {
            throw new InvalidInputAmountException(amount);
        }
        this.balance = this.balance + amount;
        Transaction transaction = new Transaction(TransactionType.CREDIT, this.accountNumber, 0, amount);
        transactionList.add(transaction);

    }

    protected void debit(double amount) throws InvalidInputAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidInputAmountException(amount);
        }

        if(this.balance < amount) {
            throw new InsufficientFundsException(amount);
        }

        this.balance = this.balance - amount;
        Transaction transaction = new Transaction(TransactionType.DEBIT, this.accountNumber, 0, amount);
        transactionList.add(transaction);

    }

    protected void transferAmount(BankAccount destination, double amount) throws InvalidInputAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidInputAmountException(amount);
        }

        if(this.balance < amount) {
            throw new InsufficientFundsException(amount);
        }

        this.balance = this.balance - amount;
        destination.balance = destination.balance + amount;
        Transaction debitTransaction = new Transaction(TransactionType.DEBIT, this.getAccountNumber(), destination.getAccountNumber(), amount);
        Transaction creditTransaction = new Transaction(TransactionType.CREDIT, destination.getAccountNumber(), this.getAccountNumber(), amount);
        transactionList.add(debitTransaction);
        transactionList.add(creditTransaction);
    }

    public static List<Transaction> ListTransactions() {
        return  transactionList;
    }
}
