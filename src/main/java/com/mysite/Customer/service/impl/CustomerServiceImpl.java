package com.mysite.Customer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysite.Customer.dto.CustomerDto;
import com.mysite.Customer.mapper.CustomerMapper;
import com.mysite.Customer.model.Customer;
import com.mysite.Customer.model.FileType;
import com.mysite.Customer.model.RealCustomer;
import com.mysite.Customer.service.CustomerService;
import com.mysite.Customer.service.exception.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {


    private static final CustomerServiceImpl INSTANCE;

    public static CustomerServiceImpl getInstance() {
        return INSTANCE;
    }

    static {
        INSTANCE = new CustomerServiceImpl();
    }

    private CustomerServiceImpl() {


    }

    private ArrayList<Customer> customers = new ArrayList<>();


    @Override
    public void deletedCustomerById(Integer id) throws CustomerNotException {
        getCustomerById(id).setDeleted(true);


    }


    @Override
    public List<Customer> searchAndCustomerByName(String name) {
        return customers.stream()
                .filter(customer -> !customer.getDeleted())
                .filter(customer -> customer.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

    }


    @Override
    public Customer getCustomerById(Integer id) throws CustomerNotException {
        return customers.stream()
                .filter(customer -> !customer.getDeleted())
                .filter(customer -> customer.getId().equals(id))
                .findFirst().orElseThrow(CustomerNotException::new);

    }


    @Override
    public List<Customer> searchCustomerByFamily(String family) {
        return customers.stream()
                .filter(customer -> !customer.getDeleted())
                .filter(customer -> customer instanceof RealCustomer)
                .map(customer -> (RealCustomer) customer)
                .filter(realCustomer -> realCustomer.getFamily().equalsIgnoreCase(family))
                .collect(Collectors.toList());

    }


    @Override
    public void addCustomer(Customer customer) throws DuplicateCustomerException, ValidationException {
        List<Customer> collect = customers.stream()
                .filter(it -> it.equals(customer))
                .findAny()
                .stream().toList();
        if (!collect.isEmpty()) {
            throw new DuplicateCustomerException();
        }
        customers.add(customer);

    }


    @Override
    public List<Customer> getActiveCustomer() throws EmptyCustomerException {
        final List<Customer> collect = customers.stream()
                .filter(customer -> !customer.getDeleted())
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new EmptyCustomerException();

        }
        return collect;

    }


    @Override
    public List<Customer> getDeletedCustomer() throws EmptyCustomerException {
        final List<Customer> collect = customers.stream()
                .filter(Customer::getDeleted)
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new EmptyCustomerException();
        }
        return collect;


    }

    @Override
    public void saveData(String name, FileType type) throws FileException {
        switch (type) {
            case FileType.SERIALIZE -> saveSerialize(name);
            case FileType.JSON -> saveJson(name);
        }

    }
    private void saveJson(String name) throws FileException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(name + ".json");
            if (!file.exists()) file.createNewFile();

            List<CustomerDto> dtoList = CustomerMapper.mapToCustomerDtoList(customers);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, dtoList);

        } catch (IOException e) {
            throw new FileException();
        }
    }





    private void saveSerialize(String name) throws FileException {
        try {
            File file = new File(name + ".com");
            file.createNewFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(customers);
            }
        } catch (IOException e) {
            throw new FileException();

        }
    }

    @Override
    public void loadData(String name, FileType type) throws FileException {
        switch (type) {
            case FileType.SERIALIZE -> loadSerialize(name);
            case FileType.JSON -> loadJson(name);
        }

    }

    private void loadJson(String name) throws FileException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(name + ".json");

            if (!file.exists() || file.length() == 0) {
                customers = new ArrayList<>();
                return;
            }

            List<CustomerDto> dtoList = objectMapper.readValue(
                    file,
                    new com.fasterxml.jackson.core.type.TypeReference<List<CustomerDto>>() {
                    }
            );

            customers = new ArrayList<>();
            for (CustomerDto dto : dtoList) {
                customers.add(CustomerMapper.mapToCustomer(dto));
            }

        } catch (Exception e) {
            throw new FileException();
        }
    }


    private void loadSerialize(String name) throws FileException {
        try {
            try (FileInputStream fileInputStream = new FileInputStream(name + ".com");
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                customers = (ArrayList<Customer>) objectInputStream.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new FileException();

        }
    }


}