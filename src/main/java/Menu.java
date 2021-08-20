import java.util.List;
import java.util.Scanner;

public class Menu {
    public void show() throws
            InvalidAccountTypeException,
            AccountNotFoundException
    {
        int option;
        BankService service = new BankService();
        List<Account> listAct;
        Account account;
        int accountNumber;
        int amount;
        int confirm;

        Scanner scanner = new Scanner(System.in);
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Java Bank \n");
        System.out.println("Choose your option \n");
        System.out.println("1 - Create Account");
        System.out.println("2 - Deposit Money");
        System.out.println("3 - Withdraw Money");
        System.out.println("4 - Transfer Money");
        System.out.println("5 - List Accounts");
        System.out.println("6 - List Transactions");
        System.out.println("7 - Exit");

        do {
            System.out.println("---------------------------------------------------");
            System.out.println("Enter your option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Create Account");
                    System.out.println("Enter your Name");
                    String name = input.nextLine();
                    System.out.println("Enter your Address");
                    String address = input.nextLine();
                    System.out.println("Enter your Mobile Number");
                    String mobile = input.nextLine();
                    System.out.println("Enter Account type (savings/current)?");
                    String accountType = input.nextLine();

                    account = new Account(name, address, mobile, accountType);
                    service.createAccount(account);

                    System.out.println("Account Created Successfully! \n Account Number: "+ account.getAccountNumber());

                    break;
                case 2:
                    System.out.println("Enter Customer Account Number");
                    accountNumber = input.nextInt();
                    System.out.println("Enter Amount to deposit");
                    amount = input.nextInt();

                    account = service.getAccountByNumber(accountNumber);

                    service.deposit(account, amount);
                    System.out.println("** Amount Credited Successfully! **");
                    break;
                case 3:
                    System.out.println("Enter Customer Account Number");
                    accountNumber = input.nextInt();
                    System.out.println("Enter Amount to withdraw");
                    amount = input.nextInt();

                    account = service.getAccountByNumber(accountNumber);

                    service.withdraw(account, amount);
                    System.out.println("** Amount Debited Successfully! **");
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
                            Account fromAccount = service.getAccountByNumber(accountNumber);
                            Account toAccount = service.getAccountByNumber(accountNumberTo);

                            service.transfer(fromAccount, toAccount, amount);
                            System.out.println("** Amount Transfer Successful! **");
                        } else {
                            System.out.println("Transaction is cancelled!");
                        }
                    } else {
                        Account fromAccount = service.getAccountByNumber(accountNumber);
                        Account toAccount = service.getAccountByNumber(accountNumberTo);

                        service.transfer(fromAccount, toAccount, amount);
                        System.out.println("** Amount Transfer Successful! **");
                    }



                    break;
                case 5:
                    System.out.println("Accounts List");
                    System.out.println("----------------------------------------------");
                    listAct = service.listAccounts();

                    for (Account act : listAct) {
                        System.out.println(act.getAccountNumber()+" "+act.getName()+" "+act.getAddress()+" "+act.getMobile()+" "+act.getType()+" "+act.getBalance());
                    }
                    break;
                case 6:
                    System.out.println("Transactions List");
                    System.out.println("----------------------------------------------");
                    List<Transaction> transactionList = service.ListTransactions();

                    for (Transaction trans : transactionList) {
                        System.out.println(trans.getId()+" "+trans.getAmount()+" "+trans.getType()+" "+trans.getDate()+" "+trans.getFromAccount()+" "+trans.getToAccount());
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
