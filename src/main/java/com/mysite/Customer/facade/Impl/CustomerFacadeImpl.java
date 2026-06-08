package com.mysite.Customer.facade.Impl;

import com.mysite.Customer.dto.CustomerDto;
import com.mysite.Customer.facade.CustomerFacade;
import com.mysite.Customer.mapper.CustomerMapper;
import com.mysite.Customer.model.Customer;
import com.mysite.Customer.model.FileType;
import com.mysite.Customer.service.CustomerService;
import com.mysite.Customer.service.Validation.ValidationContext;
import com.mysite.Customer.service.exception.*;
import com.mysite.Customer.service.impl.CustomerServiceImpl;

import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {
    private ValidationContext<CustomerDto> validationContext;

    private final CustomerService customerService;

    private static final CustomerFacadeImpl INSTANCE;
    public static CustomerFacadeImpl getInstance(){
        return INSTANCE;
    }
    static {
        INSTANCE = new CustomerFacadeImpl();
    }

    private CustomerFacadeImpl() {
        this.customerService = CustomerServiceImpl.getInstance();
        this.validationContext = new CustomerValidationContext();
    }

    @Override
    public void deletedCustomerById(Integer id) throws CustomerNotException {
        customerService.deletedCustomerById(id);


    }

    @Override
    public List<CustomerDto> searchAndCustomerByName(String name) {
        return CustomerMapper.mapToCustomerDtoList(
                customerService.searchAndCustomerByName(name)

        );
    }

    @Override
    public CustomerDto getCustomerById(Integer id) throws CustomerNotException {
        return CustomerMapper.mapToCustomerDto(
                customerService.getCustomerById(id)
        );
    }

    @Override
    public List<CustomerDto> searchCustomerByFamily(String family) {
        return CustomerMapper.mapToCustomerDtoList(
                customerService.searchCustomerByFamily(family)
        );
    }

    @Override
    public void addCustomer(CustomerDto customer) throws DuplicateCustomerException, ValidationException {
        validationContext.validate(customer);
        customerService.addCustomer(CustomerMapper.mapToCustomer(customer));

    }

    @Override
    public void updateCustomerDto(CustomerDto customerDto) throws ValidationException, CustomerNotException {
        validationContext.validate(customerDto);
        final Customer customer = customerService.getCustomerById(customerDto.getId());
        CustomerMapper.mapToCustomer(customerDto ,customer );
    }
 
    @Override
    public List<CustomerDto> getActiveCustomer() throws EmptyCustomerException {
        return CustomerMapper.mapToCustomerDtoList(
                customerService.getActiveCustomer()
        );
    }

    @Override
    public List<CustomerDto> getDeletedCustomer() throws EmptyCustomerException {
        return CustomerMapper.mapToCustomerDtoList(
                customerService.getDeletedCustomer()
        );
    }

    @Override
    public void saveData(String name, FileType type) throws FileException {
        customerService.saveData(name, type);
    }

    @Override
    public void loadData(String name, FileType type) throws FileException {
        customerService.loadData(name, type);
    }
}
