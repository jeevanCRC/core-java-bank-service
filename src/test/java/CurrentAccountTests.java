import com.creditrepaircloud.banking.accounts.BankAccount;
import com.creditrepaircloud.banking.accounts.CurrentAccount;
import com.creditrepaircloud.banking.accounts.SavingsAccount;
import com.creditrepaircloud.banking.accounts.Transaction;
import com.creditrepaircloud.banking.exceptions.AccountNotFoundException;
import com.creditrepaircloud.banking.exceptions.InsufficientFundsException;
import com.creditrepaircloud.banking.exceptions.InvalidAccountTypeException;
import com.creditrepaircloud.banking.exceptions.InvalidInputAmountException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CurrentAccountTests {
    private BankAccount currentAccount1;
    private BankAccount currentAccount2;

    @BeforeEach
    public void setUp() {
        currentAccount1 = new CurrentAccount(1122);
        currentAccount2 = new CurrentAccount(1123);
    }


    @Test
    void testDepositCurrentAccount() throws InvalidInputAmountException {
        currentAccount1.deposit(1000);
        currentAccount2.deposit(1200);
        assertEquals(1000, currentAccount1.getBalance());
        assertEquals(1200, currentAccount2.getBalance());
    }


    @Test
    void testWithdrawCurrentAccount() throws InvalidInputAmountException, InsufficientFundsException {
        currentAccount1.deposit(2000);
        currentAccount1.withdraw(800);
        assertEquals(1200, currentAccount1.getBalance());
    }

    @Test
    void testTransferAmountCurrentAccount() throws InvalidInputAmountException, InsufficientFundsException {
        currentAccount1.deposit(2000);
        currentAccount2.deposit(800);
        currentAccount1.transfer(currentAccount2, 400);
        assertAll(() -> {
            assertEquals(1600, currentAccount1.getBalance());
            assertEquals(1200, currentAccount2.getBalance());
        });
    }

    @Test
    void testTransferAmountCurrentAccountThrowsException() throws InvalidInputAmountException {
        currentAccount1.deposit(2000);
        currentAccount2.deposit(800);
        assertThrows(InvalidInputAmountException.class, () -> {
            currentAccount1.transfer(currentAccount2, 0);
        });
    }

    @Test
    void testTransferAmountSavingsAccountThrowsException() throws InvalidInputAmountException {
        currentAccount1.deposit(2000);
        currentAccount2.deposit(800);
        assertThrows(InvalidInputAmountException.class, () -> {
            currentAccount1.transfer(currentAccount2, 0);
        });
    }

    @Test
    void testSavingsAccountTransferInsufficientFundsException() throws InvalidInputAmountException {
        currentAccount1.deposit(1000);
        assertThrows(InsufficientFundsException.class, () -> {
            currentAccount1.transfer(currentAccount2, 2000);
        });
    }

    @Test
    void testSavingsAccountWithdrawInsufficientFundsException() throws InvalidInputAmountException {
        currentAccount1.deposit(1000);
        assertThrows(InsufficientFundsException.class, () -> {
            currentAccount1.withdraw( 2000);
        });
    }


    @AfterEach
    public void tearDown() {
        currentAccount1 = null;
        currentAccount2 = null;
    }
}
