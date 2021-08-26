package com.creditrepaircloud.banking.accounts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private int id;
    private TransactionType type;
    private Date date;
    private int fromAccount;
    private int toAccount;
    private double amount;

    private static int transIdSequence = 1000;

    public Transaction(TransactionType type, int fromAccount, int toAccount, double amount) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        this.type = type;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.id = ++transIdSequence;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public int getFromAccount() {
        return fromAccount;
    }


    public int getToAccount() {
        return toAccount;
    }

    public double getAmount() {
        return this.amount;
    }

}
