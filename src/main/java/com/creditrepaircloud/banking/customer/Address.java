package com.creditrepaircloud.banking.customer;

import com.creditrepaircloud.banking.exceptions.InvalidAddressException;
import netscape.javascript.JSObject;

import java.util.HashMap;
import java.util.Map;

public class Address {
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    public Address(Map<String, String> o) throws InvalidAddressException {
        AddressValidatorFactory.validate(o);
        this.line1 = o.get("line1").toString();
        this.line2 = o.get("line2").toString();
        this.city = o.get("city").toString();
        this.state = o.get("state").toString();
        this.country = o.get("country").toString();
        this.zipcode = o.get("zipcode").toString();
    }


}
