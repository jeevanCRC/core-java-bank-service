import com.creditrepaircloud.banking.accounts.Transaction;
import com.creditrepaircloud.banking.accounts.TransactionType;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionTests {

    @Test
    void testTransactionObject() {
        Transaction transaction = new Transaction(TransactionType.DEBIT, 123, 345, 1000);
        assertAll(() -> {
            assertTrue(transaction.getId() > 0);
            assertEquals(1000, transaction.getAmount());
            assertEquals(1000, transaction.getAmount());
            assertEquals(TransactionType.DEBIT, transaction.getType());
            assertEquals(123, transaction.getFromAccount());
            assertEquals(345, transaction.getToAccount());
            assertNotNull(transaction.getDate());
        });

    }
}
