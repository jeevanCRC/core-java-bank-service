package services;

import com.creditrepaircloud.banking.accounts.BankAccount;
import com.creditrepaircloud.banking.accounts.SavingsAccount;
import com.creditrepaircloud.banking.customer.AccountHolder;
import com.creditrepaircloud.banking.customer.Address;
import com.creditrepaircloud.banking.exceptions.InsufficientFundsException;
import com.creditrepaircloud.banking.exceptions.InvalidInputAmountException;
import com.creditrepaircloud.banking.exceptions.InvalidInputException;
import com.creditrepaircloud.banking.services.AccountTransfer;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountTransferTests {

    private final AccountTransfer transferService = new AccountTransfer();

    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    public void setUp() throws InvalidInputException {
        AccountHolder customer1 = new AccountHolder("Test One", "8738838239");
        AccountHolder customer2 = new AccountHolder("Test Two", "8738838239");

        account1 = new SavingsAccount(customer1.getId());
        customer1.createAccount(account1);

        account2 = new SavingsAccount(customer2.getId());
        customer2.createAccount(account2);
    }

    @Test
    void testTransferAmountWithPositiveValues() throws InsufficientFundsException, InvalidInputAmountException {
        account1.deposit(2000);
        transferService.transferAmount(account1, account2, 800);
        assertAll(() -> {
            assertEquals(800, account2.getBalance());
            assertEquals(1200, account1.getBalance());
        });

    }

    @Test
    void testTransferAmountWithPositiveValues2() throws InsufficientFundsException, InvalidInputAmountException {
        account2.deposit(2000);
        transferService.transferAmount(account2, account1, 500);
        assertAll(() -> {
            assertEquals(1500, account2.getBalance());
            assertEquals(500, account1.getBalance());
        });
    }

    @Test
    void testTransferAmountWithInsufficientFundsException() throws InsufficientFundsException, InvalidInputAmountException {
        account2.deposit(2000);
        assertAll(() -> {
            assertEquals(2000, account2.getBalance());
            assertThrows(InsufficientFundsException.class, () -> {
                transferService.transferAmount(account2, account1, 2500);
            });
        });
    }

    @Test
    void testTransferAmountWithZeroAmount() throws InsufficientFundsException, InvalidInputAmountException {
        account2.deposit(2000);
        assertAll(() -> {
            assertEquals(2000, account2.getBalance());
            assertThrows(InvalidInputAmountException.class, () -> {
                transferService.transferAmount(account2, account1, 0);
            });
        });

    }

}
