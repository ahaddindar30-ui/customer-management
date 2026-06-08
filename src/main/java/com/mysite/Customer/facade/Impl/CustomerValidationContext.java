package com.mysite.Customer.facade.Impl;

import com.mysite.Customer.dto.CustomerDto;
import com.mysite.Customer.dto.LegalCustomerDto;
import com.mysite.Customer.dto.RealCustomerDto;

import com.mysite.Customer.service.Validation.ValidationContext;
import com.mysite.Customer.service.exception.ValidationException;
import com.mysite.Customer.util.NumberValidator;


public class CustomerValidationContext extends ValidationContext<CustomerDto> {
    public CustomerValidationContext(){
        addValidation(customer -> {
            String name = customer.getName();
            if (name == null ||
                    name.trim().isEmpty()){
                throw new ValidationException("name must not be empty or null.");
            }
        });

        addValidation(customer -> {
            String number = customer.getNumber();
            if (!NumberValidator.validate(number))
                throw new ValidationException("number must not be empty or null.");

        });

        addValidation(customer -> {
            if (customer instanceof RealCustomerDto){
                String family = ((RealCustomerDto) customer).getFamily();
                if (family == null ||
                        family.trim().isEmpty() ||
                        !family.equals(family.toLowerCase()) ){
                    throw new ValidationException("family must not be empty or null, and should by in lower case.");
                }
            }
        });

        addValidation(customer -> {
            if (customer instanceof LegalCustomerDto){
                String fax = ((LegalCustomerDto) customer).getFax();
                if (!NumberValidator.validate(fax)){
                    throw new ValidationException("Invalid fax number format.");
                }

            }
        });


    }
}
