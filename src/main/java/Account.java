import java.util.Objects;

public class Account {
    private int accountNumber;
    private String name;
    private String address;
    private String mobile;
    private String type;
    private int balance;

    private static int accountNumberSequence = 1234;

    public Account(String name, String address, String mobile, String type) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.type = type;
        this.accountNumber = ++accountNumberSequence;
        this.balance = 0;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getAccountNumber() {
        return this.accountNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getMobile() {
        return this.mobile;
    }

    public int getBalance() {
        return this.balance;
    }

    public String getType() {
        return this.type;
    }

    public void deposit(int amount) {
        if(amount > 0) {
            this.balance = this.balance + amount;
        }
    }

    public void withdraw(int amount) {
        if(amount > 0) {
            this.balance = this.balance - amount;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber == account.accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
