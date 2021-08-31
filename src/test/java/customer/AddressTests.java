package customer;

import com.creditrepaircloud.banking.customer.AccountHolder;
import com.creditrepaircloud.banking.customer.Address;
import com.creditrepaircloud.banking.exceptions.InvalidAddressException;
import com.creditrepaircloud.banking.exceptions.InvalidInputException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTests {

    private AccountHolder customer;

    @BeforeEach
    public void setUp() throws InvalidInputException {
        customer = new AccountHolder("Test One", "8738838239");
    }

    @Test
    void testCustomerAddress() throws InvalidAddressException {
        Map<String, String> obj=new HashMap<String, String>();
        obj.put("line1", "123-2");
        obj.put("line2", "test");
        obj.put("city", "Test City");
        obj.put("state", "State");
        obj.put("country", "India");
        obj.put("zipcode", "823892398");
        Address address = new Address(obj);
        customer.setAddress(address);

        assertEquals(address, customer.getAddress());
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n",""})
    @DisplayName("Address line1 empty")
    void testCustomerAddressThrowsException(String value) throws InvalidAddressException {
        Map<String, String> obj=new HashMap<String, String>();
        obj.put("line1", value);
        assertThrows(InvalidAddressException.class, () -> {
            Address address = new Address(obj);
        });
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n",""})
    @DisplayName("Address City empty")
    void testCustomerAddressThrowsException2(String value) throws InvalidAddressException {
        Map<String, String> obj=new HashMap<String, String>();
        obj.put("line1", "1-2-3");
        obj.put("city", value);
        assertThrows(InvalidAddressException.class, () -> {
            Address address = new Address(obj);
        });
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n",""})
    @DisplayName("Address State empty")
    void testCustomerAddressThrowsException3(String value) throws InvalidAddressException {
        Map<String, String> obj=new HashMap<String, String>();
        obj.put("line1", "1-2-3");
        obj.put("city", "city");
        obj.put("state", value);
        assertThrows(InvalidAddressException.class, () -> {
            Address address = new Address(obj);
        });
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n",""})
    @DisplayName("Address Country empty")
    void testCustomerAddressThrowsException4(String value) throws InvalidAddressException {
        Map<String, String> obj=new HashMap<String, String>();
        obj.put("line1", "1-2-3");
        obj.put("city", "city");
        obj.put("state", "State");
        obj.put("country", value);
        assertThrows(InvalidAddressException.class, () -> {
            Address address = new Address(obj);
        });
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n",""})
    @DisplayName("Address Zipcode empty")
    void testCustomerAddressThrowsException5(String value) throws InvalidAddressException {
        Map<String, String> obj=new HashMap<String, String>();
        obj.put("line1", "1-2-3");
        obj.put("city", "city");
        obj.put("state", "State");
        obj.put("country", "Country");
        obj.put("zipcode", value);
        assertThrows(InvalidAddressException.class, () -> {
            Address address = new Address(obj);
        });
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Address parameter is null")
    void testCustomerAddressNullThrowsException(String value) throws NullPointerException {
        Map<String, String> obj=new HashMap<String, String>();
        obj.put("line1", "1-2-3");
        obj.put("city", "city");
        obj.put("state", "State");
        obj.put("country", "Country");
        obj.put("zipcode", value);
        assertThrows(NullPointerException.class, () -> {
            Address address = new Address(obj);
        });
    }


}
