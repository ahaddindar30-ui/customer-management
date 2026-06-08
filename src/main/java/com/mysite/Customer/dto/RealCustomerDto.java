package com.mysite.Customer.dto;

import com.mysite.Customer.model.CustomerType;

import java.io.Serializable;

public class RealCustomerDto extends CustomerDto implements Serializable {

    private String family;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    @Override
    public String toString() {
        return "RealCustomer{" +
                super.toString()+
                " .family='" + family + '\'' +
                '}';
    }

    public RealCustomerDto() {
        super(null, null, null, CustomerType.LEGAL);
    }
    public RealCustomerDto(Integer id , String name, String number) {
        super(id , name, number, CustomerType.REAL);
    }
}
