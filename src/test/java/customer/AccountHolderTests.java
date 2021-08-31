package customer;

import com.creditrepaircloud.banking.accounts.BankAccount;
import com.creditrepaircloud.banking.accounts.SavingsAccount;
import com.creditrepaircloud.banking.customer.AccountHolder;
import com.creditrepaircloud.banking.customer.Address;
import com.creditrepaircloud.banking.exceptions.AccountNotFoundException;
import com.creditrepaircloud.banking.exceptions.InvalidAddressException;
import com.creditrepaircloud.banking.exceptions.InvalidInputAmountException;
import com.creditrepaircloud.banking.exceptions.InvalidInputException;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AccountHolderTests {

    private AccountHolder customer;

    @BeforeEach
    public void setUp() throws InvalidInputException {
        customer = new AccountHolder("Test One", "8738838239");
    }

    @Test
    void testCustomerInvalidInputException() {
        assertAll(() -> {
            assertThrows(InvalidInputException.class, () -> { new AccountHolder("", ""); });
            assertThrows(InvalidInputException.class, () -> { new AccountHolder("", "9499372892"); });
            assertThrows(InvalidInputException.class, () -> { new AccountHolder("Test One", ""); });
        });

    }

    @Test
    void testCustomerWithPositiveCase() throws InvalidInputException {
        AccountHolder customer2 = new AccountHolder("Test Two", "8738838239");
        assertAll(() -> {
            assertEquals("Test Two", customer2.getName());
            assertEquals("8738838239", customer2.getMobile());
            assertTrue(customer2.getId() > 1000);
        });
    }

    @Test
    void testCreateAccount() throws InvalidInputException {
        AccountHolder customer3 = new AccountHolder("Test Three", "8738838239");
        BankAccount account =new SavingsAccount(customer3.getId());
        customer3.createAccount(account);

        assertEquals(customer3.getId(), account.getCustomerId());
    }

    @Test
    void testGetAccountNumberPositive() throws InvalidInputException, AccountNotFoundException {
        AccountHolder customer3 = new AccountHolder("Test Three", "8738838239");
        BankAccount account =new SavingsAccount(customer3.getId());
        customer3.createAccount(account);
        BankAccount account1 =customer3.getAccountByNumber(account.getAccountNumber());

        assertEquals(account, account1);
    }

    @Test
    void testGetAccountNumberThrowsException() throws AccountNotFoundException {
        assertThrows(AccountNotFoundException.class, () -> {
            BankAccount account1 =customer.getAccountByNumber(123);
        });
    }

    @Test
    void testListAccountsWithNoAccounts() {
        List<BankAccount> accounts =customer.listAccounts();
        System.out.println(accounts.size());
        assertFalse(accounts.size() > 0);
    }

    @Test
    void testListAccountsWithAddedAccounts() throws InvalidInputException {
        AccountHolder customer3 = new AccountHolder("Test Three", "8738838239");
        BankAccount account =new SavingsAccount(customer3.getId());
        customer3.createAccount(account);
        List<BankAccount> accounts =customer3.listAccounts();
        assertTrue(accounts.size() > 0);
    }

}
