package com.mysite.Customer.service.exception;

public class EmptyCustomerException extends CustomerBaseException {
    public EmptyCustomerException(){
        super("there is no customer!");
    }
}
