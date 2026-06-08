package com.mysite.Customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


import java.util.concurrent.atomic.AtomicInteger;

public abstract class Customer {
   private String name;
   private String number;
   private final CustomerType type;
   private final Integer id;
   private Boolean deleted;

   private static final AtomicInteger ID_COUNTER = new AtomicInteger(1);




    public Customer(String name, String number, CustomerType type) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.id = ID_COUNTER.getAndIncrement();
        this.deleted = false;
    }

    private String capitalizeFirstCharacter(String str){
        if (str != null && !str.isEmpty()){
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
        return str;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = capitalizeFirstCharacter(name);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CustomerType getType() {
        return type;
    }


    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", id='" + id + '\'' +
                ", type=" + type ;
    }
}
