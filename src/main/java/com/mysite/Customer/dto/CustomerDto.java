package com.mysite.Customer.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mysite.Customer.model.CustomerType;

import java.io.Serializable;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RealCustomerDto.class, name = "REAL"),
        @JsonSubTypes.Type(value = LegalCustomerDto.class, name = "LEGAL")
})
public abstract class CustomerDto implements Serializable {
    private String name;
    private String number;
    private  CustomerType type;
    private  Integer id;

    public CustomerDto(Integer id ,String name, String number, CustomerType type) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.id = id;

    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", id='" + id + '\'' +
                ", type=" + type ;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public CustomerType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
