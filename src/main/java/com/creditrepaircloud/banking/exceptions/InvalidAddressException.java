package com.creditrepaircloud.banking.exceptions;

public class InvalidAddressException extends Exception {
    private final String message;

    public InvalidAddressException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return "InvalidAddressException{" +
                "msg='" + message + '\'' +
                '}';
    }
}
