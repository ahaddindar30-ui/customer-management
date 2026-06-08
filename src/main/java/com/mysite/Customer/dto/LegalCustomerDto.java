package com.mysite.Customer.dto;

import com.mysite.Customer.model.CustomerType;

import java.io.Serializable;

public class LegalCustomerDto extends CustomerDto implements Serializable {
    private String fax;

    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "LegalCustomer{" +
                super.toString()+
                " ,fax='" + fax + '\'' +
                '}';
    }

    public LegalCustomerDto() {
        super(null, null, null, CustomerType.LEGAL);
    }
    public LegalCustomerDto(Integer id ,String name, String number) {
        super(id,name, number, CustomerType.LEGAL);
    }
}
