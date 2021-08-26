import com.creditrepaircloud.banking.accounts.BankAccount;
import com.creditrepaircloud.banking.accounts.CurrentAccount;
import com.creditrepaircloud.banking.accounts.SavingsAccount;
import com.creditrepaircloud.banking.accounts.Transaction;
import com.creditrepaircloud.banking.customer.AccountHolder;
import com.creditrepaircloud.banking.exceptions.*;
import com.creditrepaircloud.banking.services.AccountTransfer;
import com.creditrepaircloud.banking.services.CoreBankingServices;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public void show() throws
            InvalidAccountTypeException,
            AccountNotFoundException, InvalidInputAmountException, InsufficientFundsException, InvalidInputException {
        int option;
        CoreBankingServices service = new AccountTransfer();
        BankAccount account;
        int accountNumber;
        int amount;
        int confirm;
        AccountHolder customer;

        Scanner scanner = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        System.out.println("Welcome to Java Bank \n");
        System.out.println("Choose your option \n");
        System.out.println("1 - Create Account");
        System.out.println("2 - Deposit Money");
        System.out.println("3 - Withdraw Money");
        System.out.println("4 - Transfer Money");
        System.out.println("5 - List Transactions");
        System.out.println("6 - Check Balance");
        System.out.println("7 - Exit");

        do {
            System.out.println("---------------------------------------------------");
            System.out.println("Enter your option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Create Account");
                    System.out.println("Enter your Name");
                    String name = input2.nextLine();
                    System.out.println("Enter your Mobile Number");
                    String mobile = input2.nextLine();
                    System.out.println("Enter Account type (savings/current)?");
                    String accountType = input2.nextLine();

                    customer = new AccountHolder(name, mobile);

                    switch (accountType) {
                        case "savings":
                            account = new SavingsAccount(customer.getId());
                            customer.createAccount(account);
                            System.out.println("Savings Account Created Successfully! \n Account Number: "+ account.getAccountNumber());
                            break;
                        case "current":
                            account = new CurrentAccount(customer.getId());
                            customer.createAccount(account);
                            System.out.println("Current Account Created Successfully! \n Account Number: "+ account.getAccountNumber());
                            break;
                        default:
                            System.out.println("Invalid Account type specified");
                            break;
                    }

                    break;
                case 2:
                    System.out.println("Enter Customer Account Number");
                    accountNumber = input.nextInt();
                    System.out.println("Enter Amount to deposit");
                    amount = input.nextInt();
                    System.out.println(accountNumber);

                    account = AccountHolder.getAccountByNumber(accountNumber);
                    if (account != null) {
                        account.deposit(amount);
                        System.out.println("** Amount Credited Successfully! **");
                    }


                    break;
                case 3:
                    System.out.println("Enter Customer Account Number");
                    accountNumber = input.nextInt();
                    System.out.println("Enter Amount to withdraw");
                    amount = input.nextInt();

                    account = AccountHolder.getAccountByNumber(accountNumber);
                    if (account != null) {
                        account.withdraw(amount);
                        System.out.println("** Amount Debited Successfully! **");
                    }


                    break;
                case 4:
                    System.out.println("Transfer Money \n -----------------------------------");
                    System.out.println("Enter Source Account Number");
                    accountNumber = input.nextInt();

                    System.out.println("Enter Destination Account Number");
                    int accountNumberTo = input.nextInt();

                    System.out.println("Enter Amount to transfer");
                    amount = input.nextInt();

                    if(amount > 5000) {
                        System.out.println("Confirm transfer? \n 1 - Yes \n 2 - No");
                        confirm = input.nextInt();
                        if(confirm == 1) {
                            BankAccount fromAccount = AccountHolder.getAccountByNumber(accountNumber);
                            BankAccount toAccount = AccountHolder.getAccountByNumber(accountNumberTo);
                            service.transferAmount(fromAccount, toAccount, amount);
                            System.out.println("** Amount Transfer Successful! **");
                        } else {
                            System.out.println("Transaction is cancelled!");
                        }
                    } else {
                        BankAccount fromAccount = AccountHolder.getAccountByNumber(accountNumber);
                        BankAccount toAccount = AccountHolder.getAccountByNumber(accountNumberTo);
                        service.transferAmount(fromAccount, toAccount, amount);
                        System.out.println("** Amount Transfer Successful! **");
                    }



                    break;
                case 5:
                    System.out.println("Transactions List");
                    System.out.println("----------------------------------------------");
                    List<Transaction> transactionList = BankAccount.ListTransactions();

                    for (Transaction trans : transactionList) {
                        System.out.println(trans.getId()+" "+trans.getAmount()+" "+trans.getType()+" "+trans.getDate()+" "+trans.getFromAccount()+" "+trans.getToAccount());
                    }
                    break;
                case 6:
                    System.out.println("Check Balance");
                    System.out.println("Enter Customer Account Number");
                    accountNumber = input.nextInt();

                    account = AccountHolder.getAccountByNumber(accountNumber);

                    if(account != null) {
                        System.out.println("Available Balance: "+ account.getBalance());
                    }
                    break;
                case 7:
                    System.out.println("****************************************************");
                    break;
                default:
                    System.out.println("Invalid Option!. Please try again.");
                    break;
            }
        } while (option != 7);
        System.out.println("Thank you!");
    }
}
