
import java.util.LinkedList;
import java.util.List;

public class BankService {

    List<Account> accountList = new LinkedList<>();
    List<Transaction> transactionList = new LinkedList<>();

    public void createAccount(Account account) throws InvalidAccountTypeException {
       if(!account.getType().equalsIgnoreCase("savings") &&
               !account.getType().equalsIgnoreCase("current")) {
               throw new InvalidAccountTypeException(account.getType());
       }
        accountList.add(account);
    }

    public void updateAccount(Account account) throws AccountNotFoundException {
        if(!accountList.contains(account)) {
                throw new AccountNotFoundException(account.getAccountNumber());
        }
        int index = accountList.indexOf(account);
        accountList.set(index, account);
    }

    public List<Account> listAccounts() {
        return accountList;
    }

    protected void createTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public List<Transaction> ListTransactions() {
        return  transactionList;
    }

    public void deposit(Account account, int amount) throws AccountNotFoundException {
        if(!accountList.contains(account)) {
            throw new AccountNotFoundException(account.getAccountNumber());
        }
        account.deposit(amount);
        this.createTransaction(new Transaction("credit", account.getAccountNumber(), 0, amount));
    }

    public void withdraw(Account account, int amount) throws AccountNotFoundException {
        if(!accountList.contains(account)) {
            throw new AccountNotFoundException(account.getAccountNumber());
        }

        account.withdraw(amount);
        this.createTransaction(new Transaction("debit", account.getAccountNumber(), 0, amount));
    }

    public void transfer(Account fromAccount, Account toAccount, int amount) throws AccountNotFoundException {
        if(!accountList.contains(fromAccount)) {
            throw new AccountNotFoundException(fromAccount.getAccountNumber());
        }
        if(!accountList.contains(toAccount)) {
            throw new AccountNotFoundException(toAccount.getAccountNumber());
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        this.createTransaction(new Transaction("debit", fromAccount.getAccountNumber(), toAccount.getAccountNumber(), amount));
        this.createTransaction(new Transaction("credit", toAccount.getAccountNumber(), fromAccount.getAccountNumber(), amount));
    }

    public Account getAccountByNumber(int accountNumber) {
        for (Account account : accountList) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}
