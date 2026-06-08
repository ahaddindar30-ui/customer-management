package com.mysite.Customer.model;

import java.io.Serializable;

public class LegalCustomer extends Customer  {

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
                super.toString() +
                " ,fax='" + fax + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RealCustomer &&
                ((RealCustomer) obj).getName().equals(getName());


    }





    public LegalCustomer(String name, String number) {
        super(name, number, CustomerType.LEGAL);
    }
}

