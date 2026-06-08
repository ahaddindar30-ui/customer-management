package com.mysite.Customer.view.ComponentUI;

import com.mysite.Customer.dto.CustomerDto;
import com.mysite.Customer.dto.RealCustomerDto;
import java.util.function.Function;



public class RealCustomerUI extends AbstractCustomerUI{
    public RealCustomerUI() {
        super();
    }

    @Override
    public CustomerDto additionalGenerateCustomer(String name , String number) {
        final String family = scannerWrapper.getUserInput("Enter family: ", Function.identity());
        RealCustomerDto realCustomer = new RealCustomerDto(null,name, number);
        realCustomer.setFamily(family);
        return realCustomer;
    }

    @Override
    public void editCustomer(CustomerDto customer) {
        RealCustomerDto realCustomer = (RealCustomerDto) customer;
        final String number = scannerWrapper.getUserInput("Enter new phone number: ", Function.identity());
        customer.setNumber(number);
        final String family = scannerWrapper.getUserInput("Enter new family: ", Function.identity());
        realCustomer.setFamily(family);

    }
}
