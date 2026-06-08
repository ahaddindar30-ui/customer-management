package com.mysite.Customer.service.exception;

public class CustomerNotException extends CustomerBaseException {
    public CustomerNotException(){
        super("Customer not find exception!");
    }
}
