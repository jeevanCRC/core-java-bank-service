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

public class AccountTests {
    private BankAccount savingsAccount1;
    private BankAccount savingsAccount2;
    private BankAccount currentAccount1;
    private BankAccount currentAccount2;

    @BeforeEach
    public void setUp() {
        savingsAccount1 = new SavingsAccount(121);
        savingsAccount2 = new SavingsAccount(122);
        currentAccount1 = new CurrentAccount(1122);
        currentAccount2 = new CurrentAccount(1123);
    }


    @Test
    void testGetAccountNumber()  {
       assertEquals(121, savingsAccount1.getCustomerId());
       assertEquals(1122, currentAccount1.getCustomerId());
    }


    @Test
    void getTransactions_positive() throws AccountNotFoundException, InvalidInputAmountException {
        savingsAccount1.deposit(2000);
        currentAccount1.deposit( 500);
        List<Transaction> transactionList = BankAccount.ListTransactions();
        assertTrue(transactionList.size() > 0);
    }

    @AfterEach
    public void tearDown() {
        savingsAccount1 = null;
        savingsAccount2 = null;
        currentAccount1 = null;
        currentAccount2 = null;
    }
}
