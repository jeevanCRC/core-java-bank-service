package com.creditrepaircloud.banking.exceptions;

public class InvalidAccountTypeException extends Exception{
    String type;

    public InvalidAccountTypeException() {
        super();
    }

    public InvalidAccountTypeException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "InvalidAccountTypeException{" +
                "type='" + type + '\'' +
                '}';
    }
}
