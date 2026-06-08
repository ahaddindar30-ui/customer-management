package com.mysite.Customer.view.ComponentUI;

import com.mysite.Customer.dto.CustomerDto;
import com.mysite.Customer.dto.LegalCustomerDto;

import java.util.function.Function;

public class LegalCustomerUI extends AbstractCustomerUI{
    public LegalCustomerUI() {
        super();
    }

    @Override
    public CustomerDto additionalGenerateCustomer(String name , String number) {
        final String fax = scannerWrapper.getUserInput("Enter fax number: ", Function.identity());
        LegalCustomerDto legalCustomer = new LegalCustomerDto(null,name, number);
        legalCustomer.setFax(fax);
        return legalCustomer;
    }

    @Override
    public void editCustomer(CustomerDto customer) {
        LegalCustomerDto legalCustomer = (LegalCustomerDto) customer;
        final String number = scannerWrapper.getUserInput("Enter new phone number: ", Function.identity());
        customer.setNumber(number);
        final String fax = scannerWrapper.getUserInput("Enter new fax number: ", Function.identity());
        legalCustomer.setFax(fax);

    }
}
