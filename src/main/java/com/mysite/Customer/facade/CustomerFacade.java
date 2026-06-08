package com.mysite.Customer.facade;

import com.mysite.Customer.dto.CustomerDto;
import com.mysite.Customer.model.FileType;
import com.mysite.Customer.service.exception.*;

import java.util.List;

public interface CustomerFacade {
    void deletedCustomerById(Integer id) throws CustomerNotException;
    List<CustomerDto> searchAndCustomerByName(String name);
    CustomerDto getCustomerById(Integer id) throws CustomerNotException;
    List<CustomerDto> searchCustomerByFamily(String family);
    void addCustomer(CustomerDto customer) throws DuplicateCustomerException, ValidationException;
    void updateCustomerDto(CustomerDto customer) throws ValidationException, CustomerNotException;
    List<CustomerDto> getActiveCustomer() throws EmptyCustomerException;
    List<CustomerDto> getDeletedCustomer() throws EmptyCustomerException;
    void saveData(String name, FileType type) throws FileException;

    void loadData(String name, FileType fileType) throws FileException;
}
