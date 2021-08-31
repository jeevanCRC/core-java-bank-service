package com.creditrepaircloud.banking.customer;

import com.creditrepaircloud.banking.accounts.BankAccount;
import com.creditrepaircloud.banking.exceptions.InvalidInputException;
import com.creditrepaircloud.banking.exceptions.AccountNotFoundException;

import java.util.LinkedList;
import java.util.List;

public class AccountHolder {
    private final String name;
    private final String mobile;
    private Address address;
    private final int id;
    private final List<BankAccount> accounts = new LinkedList<>();

    private static int customerIdSequence = 1000;

    public AccountHolder(String name, String mobile) throws InvalidInputException {
        if(name.isEmpty() || mobile.isEmpty()){
            throw new InvalidInputException(name, mobile);
        }
        this.name = name;
        this.mobile = mobile;
        this.id = ++customerIdSequence;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public int getId() { return this.id; }

    public String getMobile() {
        return this.mobile;
    }

    public Address getAddress() {
        return this.address;
    }

    public void createAccount(BankAccount account) {
        accounts.add(account);
    }

    // Proper naming for the
    public List<BankAccount> listAccounts() {
        return this.accounts;
    }

    //Find account by number
    // function should be pretty short
    public  BankAccount getAccountByNumber(int accountNumber) throws AccountNotFoundException {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new AccountNotFoundException(accountNumber);
    }
}
