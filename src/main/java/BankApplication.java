import com.creditrepaircloud.banking.accounts.BankAccount;
import com.creditrepaircloud.banking.accounts.CurrentAccount;
import com.creditrepaircloud.banking.accounts.SavingsAccount;
import com.creditrepaircloud.banking.accounts.Transaction;
import com.creditrepaircloud.banking.customer.AccountHolder;
import com.creditrepaircloud.banking.exceptions.*;
import com.creditrepaircloud.banking.services.AccountTransfer;

import java.util.List;

class BankApplication {
    public static void main(String[] args) throws
            InvalidAccountTypeException,
            AccountNotFoundException,
            InvalidInputAmountException, InsufficientFundsException, InvalidInputException {

        Menu menu = new Menu();
        menu.show();
    }
}