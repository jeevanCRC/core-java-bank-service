import com.creditrepaircloud.banking.accounts.BankAccount;
import com.creditrepaircloud.banking.accounts.SavingsAccount;
import com.creditrepaircloud.banking.customer.AccountHolder;
import com.creditrepaircloud.banking.customer.Address;
import com.creditrepaircloud.banking.exceptions.InvalidInputAmountException;
import com.creditrepaircloud.banking.exceptions.InvalidInputException;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountHolderTests {

    private AccountHolder customer;

    @BeforeEach
    public void setUp() throws InvalidInputException {
        customer = new AccountHolder("Test One", "8738838239");
    }

    @Test
    void testCustomerInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            new AccountHolder("", "");
        });
    }

    @Test
    void testCustomerWithNameEmpty() {
        assertThrows(InvalidInputException.class, () -> {
            new AccountHolder("", "9499372892");
        });
    }

    @Test
    void testCustomerWithMobileEmptyEmpty() {
        assertThrows(InvalidInputException.class, () -> {
            new AccountHolder("Test One", "");
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
    void testCustomerAddress() {
        Address address = new Address("123-2", "test", "Test City", "State", "India", 823892398);
        customer.setAddress(address);

        assertEquals(address, customer.getAddress());
    }

    @Test
    void testCreateCustomer() throws InvalidInputException {
        AccountHolder customer3 = new AccountHolder("Test Three", "8738838239");
        BankAccount account =new SavingsAccount(customer3.getId());
        customer3.createAccount(account);

        assertEquals(customer3.getId(), account.getCustomerId());
    }

    @Test
    void testGetAccountNumberPositive() throws InvalidInputException {
        AccountHolder customer3 = new AccountHolder("Test Three", "8738838239");
        BankAccount account =new SavingsAccount(customer3.getId());
        customer3.createAccount(account);
        BankAccount account1 =AccountHolder.getAccountByNumber(account.getAccountNumber());

        assertEquals(account, account1);
    }

    @Test
    void testGetAccountNumberReturnsNull() {
        BankAccount account1 =AccountHolder.getAccountByNumber(123);
        assertNull(account1);
    }

    @Order(1)
    @Test
    void testListAccountsWithNoAccounts() {
        List<BankAccount> accounts =AccountHolder.getAccounts();
        System.out.println(accounts.size());
        assertFalse(accounts.size() > 0);
    }

    @Order(2)
    @Test
    void testListAccountsWithAddedAccounts() throws InvalidInputException {
        AccountHolder customer3 = new AccountHolder("Test Three", "8738838239");
        BankAccount account =new SavingsAccount(customer3.getId());
        customer3.createAccount(account);
        List<BankAccount> accounts =AccountHolder.getAccounts();
        assertTrue(accounts.size() > 0);
    }

    @AfterEach
    public void tearDown() {
        customer = null;
    }
}
