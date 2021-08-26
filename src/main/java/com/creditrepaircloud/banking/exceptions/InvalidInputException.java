package com.creditrepaircloud.banking.exceptions;

public class InvalidInputException extends Exception{
    private final String name;
    private final String mobile;

    public InvalidInputException(String name, String mobile) {
        super();
        this.name = name;
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "InvalidInputException{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
