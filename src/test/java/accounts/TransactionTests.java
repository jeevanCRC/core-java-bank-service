package accounts;

import com.creditrepaircloud.banking.accounts.Transaction;
import com.creditrepaircloud.banking.accounts.TransactionType;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionTests {

    @Test
    void testTransactionObject() {
        Transaction transaction = new Transaction(TransactionType.DEBIT, 123, 1000);
        String transactionString = transaction.getTransaction();
        assertAll(() -> {
            assertNotNull(transaction);
            assertTrue(transactionString.startsWith("Transaction{"));
        });


    }
}
