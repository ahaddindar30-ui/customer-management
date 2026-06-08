package com.mysite.Customer.service;

import com.mysite.Customer.model.Customer;
import com.mysite.Customer.model.FileType;
import com.mysite.Customer.service.exception.*;

import java.util.List;

public interface CustomerService {
    void deletedCustomerById(Integer id) throws CustomerNotException;
    List<Customer> searchAndCustomerByName(String name);
    Customer getCustomerById(Integer id) throws CustomerNotException;
    List<Customer> searchCustomerByFamily(String family);
    void addCustomer(Customer customer) throws DuplicateCustomerException, ValidationException;
    List<Customer> getActiveCustomer() throws EmptyCustomerException;
    List<Customer> getDeletedCustomer() throws EmptyCustomerException;
    void saveData( String name ,FileType type) throws FileException;

    void loadData( String name ,FileType type) throws FileException;
}
