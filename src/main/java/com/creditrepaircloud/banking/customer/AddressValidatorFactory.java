package com.creditrepaircloud.banking.customer;

import com.creditrepaircloud.banking.exceptions.InvalidAddressException;

import java.util.Map;

public class AddressValidatorFactory {

    public static void validate(Map<String, String> obj) throws InvalidAddressException {

        if(isValid(obj.get("line1").toString())) {
            throw new InvalidAddressException("Address line1 is empty");
        }

        if(isValid(obj.get("city").toString())) {
            throw new InvalidAddressException("City is empty");
        }

        if(isValid(obj.get("state").toString())) {
            throw new InvalidAddressException("State is empty");
        }

        if(isValid(obj.get("country").toString())) {
            throw new InvalidAddressException("Country is empty");
        }

        if(isValid(obj.get("zipcode").toString())) {
            throw new InvalidAddressException("Zipcode is should be valid and not empty");
        }
    }

    private static boolean isValid(String value) {
        if(value == null) {
            throw new NullPointerException("Value should not be null");
        }
        return value.isEmpty()
                || value.isBlank();
    }
}
