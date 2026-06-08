package com.mysite.Customer.view.ComponentUI;

import com.mysite.Customer.dto.CustomerDto;
import com.mysite.Customer.model.CustomerType;
import com.mysite.Customer.util.ScannerWrapper;

import java.util.function.Function;

public abstract class AbstractCustomerUI {

    ScannerWrapper scannerWrapper;

    public AbstractCustomerUI() {
        this.scannerWrapper = ScannerWrapper.getInstance();
    }

    public static AbstractCustomerUI fromCustomerType(CustomerType type){
        return switch (type){
            case REAL -> new RealCustomerUI();
            case LEGAL -> new LegalCustomerUI();
        };
    }
     public CustomerDto generateCustomer(){
         final String name = scannerWrapper.getUserInput("Enter name : ", Function.identity());
         final String number = scannerWrapper.getUserInput("Enter phone number: ", Function.identity());
         return additionalGenerateCustomer(name , number);
     }

    protected abstract CustomerDto additionalGenerateCustomer(String name , String number);


    public abstract void editCustomer(CustomerDto customer);



}
