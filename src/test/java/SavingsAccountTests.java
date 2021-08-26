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

public class SavingsAccountTests {
    private BankAccount savingsAccount1;
    private BankAccount savingsAccount2;

    @BeforeEach
    public void setUp() {
        savingsAccount1 = new SavingsAccount(121);
        savingsAccount2 = new SavingsAccount(122);
    }


    @Test
    void testDepositSavingsAccount() throws InvalidInputAmountException {
        savingsAccount1.deposit(1000);
        savingsAccount2.deposit(1200);
        assertEquals(1000, savingsAccount1.getBalance());
        assertEquals(1200, savingsAccount2.getBalance());
    }

    @Test
    void testDepositSavingsAccountThrowsException() throws InvalidInputAmountException {
        assertThrows(InvalidInputAmountException.class, () -> {
            savingsAccount1.deposit(0);
        });
    }

    @Test
    void testWithdrawSavingsAccountThrowsException() throws InvalidInputAmountException {
        assertThrows(InvalidInputAmountException.class, () -> {
            savingsAccount1.withdraw(0);
        });
    }

    @Test
    void testWithdrawSavingsAccount() throws InvalidInputAmountException, InsufficientFundsException {
        savingsAccount1.deposit(1000);
        savingsAccount1.withdraw(800);
        assertEquals(200, savingsAccount1.getBalance());
    }


    @Test
    void testTransferAmountSavingsAccount() throws InvalidInputAmountException, InsufficientFundsException {
        savingsAccount1.deposit(1000);
        savingsAccount2.deposit(300);
        savingsAccount1.transfer(savingsAccount2, 400);
        assertAll(() -> {
            assertEquals(600, savingsAccount1.getBalance());
            assertEquals(700, savingsAccount2.getBalance());
        });
    }

    @AfterEach
    public void tearDown() {
        savingsAccount1 = null;
        savingsAccount2 = null;
    }
}
