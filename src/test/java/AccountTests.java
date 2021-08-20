import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTests {
    BankService service = new BankService();
    private Account account;
    private Account account1;

    @BeforeEach
    public void setUp() {
        account = new Account("Jeevan One", "Hyderabad One", "89237823902", "savings");
        account1 = new Account("Jeevan Two", "Hyderabad Two", "89237823902", "savings");
    }


    @Test
    void createAccountPositive() throws InvalidAccountTypeException {
        service.createAccount(account);
        assertEquals(0, account.getBalance());
    }

    @Test
    void createAccount_ThrowsInvalidAccountTypeException() {
        assertThrows(InvalidAccountTypeException.class, () -> {
            account.setType("sav");
            service.createAccount(account);
        });
    }

    @Test
    void checkDepositPositive() throws AccountNotFoundException {
        service.deposit(account, 100);
        assertEquals(100, account.getBalance());
    }

    @Test
    void checkWithdrawPositive() throws AccountNotFoundException {
        service.deposit(account, 2000);
        service.withdraw(account, 500);
        assertEquals(1500, account.getBalance());
    }

    @Test
    void getTransactions_positive() throws AccountNotFoundException {
        service.deposit(account, 2000);
        service.withdraw(account, 500);
        List<Transaction> transactionList = service.ListTransactions();
        assertTrue(transactionList.size() > 0);
    }

    @Test
    void getTransactions_checkEmptyTransactions() {
        List<Transaction> transactionList = service.ListTransactions();
        assertFalse(transactionList.size() > 0);
    }

    @Test
    void transfer_CheckTransferAmount() throws AccountNotFoundException {
        service.deposit(account, 2000);
        service.transfer(account, account1, 500);
        List<Transaction> transactionList = service.ListTransactions();
        assertAll(() -> {
            assertEquals(500, account1.getBalance());
            assertEquals(1500, account.getBalance());
            assertTrue(transactionList.size() > 0);
        });
    }


    @ParameterizedTest
    @ValueSource(ints = {123, 234, 345})
    void getAccountNumber_Negative(int accountNumber) {
        assertNull(service.getAccountByNumber(accountNumber));
    }

    @AfterEach
    public void tearDown() {
        account = null;
    }
}
