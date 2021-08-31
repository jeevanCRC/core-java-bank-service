package com.creditrepaircloud.banking.accounts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private int id;
    private TransactionType type;
    private Date date;
    private int refNumber;
    private double amount;

    private static int transIdSequence = 1000;

    public Transaction(TransactionType type, int refNumber, double amount) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        this.type = type;
        this.refNumber = refNumber;
        this.amount = amount;
        this.id = ++transIdSequence;
        this.date = date;
    }
    
    public String getTransaction() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", date=" + date +
                ", refNumber=" + refNumber +
                ", amount=" + amount +
                '}';
    }

}
