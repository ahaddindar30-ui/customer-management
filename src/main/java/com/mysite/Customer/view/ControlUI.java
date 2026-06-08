package com.mysite.Customer.view;

import com.mysite.Customer.dto.CustomerDto;
import com.mysite.Customer.facade.CustomerFacade;
import com.mysite.Customer.facade.Impl.CustomerFacadeImpl;
import com.mysite.Customer.model.CustomerType;

import com.mysite.Customer.model.FileType;
import com.mysite.Customer.service.exception.*;
import com.mysite.Customer.util.ScannerWrapper;
import com.mysite.Customer.view.ComponentUI.AbstractCustomerUI;

import java.util.List;
import java.util.function.Function;



public  class  ControlUI implements AutoCloseable {
    private final ScannerWrapper scannerWrapper;
    private final CustomerFacade customerFacade;
    public ControlUI(){
        customerFacade = CustomerFacadeImpl.getInstance();
        scannerWrapper = ScannerWrapper.getInstance();
    }




    public void startMenu() {
        int choice;
        do {
            printMenu();
            choice = scannerWrapper.getUserInput("Enter choice: " , Integer::valueOf);
            try {
                switch (choice) {
                    case 0:
                        System.out.println("Exit.");
                        break;
                    case 1:
                        addCustomer();
                        break;
                    case 2:
                        printAllCustomer();
                        break;
                    case 3:
                        searchAndCustomerByName();
                        break;
                    case 4:
                        searchAndCustomerByFamily();
                        break;
                    case 5:
                        editCustomerById();
                        break;
                    case 6:
                        deletedCustomerById();
                        break;
                    case 7:
                        printAllDeletedCustomer();
                        break;
                    case 8:
                        saveData();
                        break;
                    case 9:
                        loadData();
                        break;
                    default:
                        System.out.println("Invalid number entered.");
                }
            }catch (CustomerNotException | FileException | EmptyCustomerException ex){
                System.out.println(ex.getMessage());
            }
        } while (choice != 0);

    }

    private void loadData() throws FileException {
        System.out.println("File type: ");
        System.out.println("1.Serialize ");
        System.out.println("2.json ");
        int choice = scannerWrapper.getUserInput("Enter choice: " ,Integer::valueOf);
        try {
            FileType fileType = FileType.fromValue(choice);
            String name = scannerWrapper.getUserInput("Enter the name : " , Function.identity());
            customerFacade.loadData(name , fileType);
        }catch (InvalidType e){
            System.out.println("Invalid customer type!");
            loadData();
        }


    }

    private void saveData() throws FileException {
        System.out.println("File type: ");
        System.out.println("1.Serialize ");
        System.out.println("2.json ");
        int choice = scannerWrapper.getUserInput("Enter choice: " ,Integer::valueOf);
        try {
            FileType fileType = FileType.fromValue(choice);
            String name = scannerWrapper.getUserInput("Enter the name : " , Function.identity());
            customerFacade.saveData(name , fileType);
        }catch (InvalidType e){
            System.out.println("Invalid customer type!");
            saveData();
        }

    }

    public void printMenu() {
        System.out.println("Menu: ");
        System.out.println("0.Exit ");
        System.out.println("1.Add customer");
        System.out.println("2.Print all customers ");
        System.out.println("3.search Customers By Name ");
        System.out.println("4.search Customers By Family ");
        System.out.println("5.Edit Customer By id");
        System.out.println("6.Deleted Customer By id ");
        System.out.println("7.Print all deleted customers ");
        System.out.println("8.save data");
        System.out.println("9.load data");
        System.out.println();
    }

    public void addCustomer() {
        System.out.println("Customer: ");
        System.out.println("1.Real ");
        System.out.println("2.Legal ");
        int choice = scannerWrapper.getUserInput("Enter choice: " , Integer::valueOf);

        try {
            customerFacade.addCustomer(AbstractCustomerUI
                    .fromCustomerType(CustomerType
                            .fromValue(choice))
                    .generateCustomer());
        } catch (DuplicateCustomerException e) {
            System.out.println("it,s not possible to select duplicate name and family.");
            addCustomer();
        }catch (InvalidType e){
            System.out.println("Invalid customer type!");
            addCustomer();
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            addCustomer();
        }


    }

    public void printAllCustomer() throws EmptyCustomerException {
        List<CustomerDto>allCustomer = customerFacade.getActiveCustomer();
        System.out.println("Add customer: ");
        for (CustomerDto customer : allCustomer) {
            System.out.println(customer);
        }

    }

    public void printAllDeletedCustomer() throws EmptyCustomerException {
        final List<CustomerDto> allCustomer = customerFacade.getDeletedCustomer();
        System.out.println("Add deleted customer: ");
        for (CustomerDto customer : allCustomer) {
            System.out.println(customer);
        }



    }

    public void searchAndCustomerByName() {
        String name = scannerWrapper.getUserInput("Enter the name : " , Function.identity());
        final List<CustomerDto> customers = customerFacade.searchAndCustomerByName(name);
        customers.forEach(System.out::println);
    }

    private void searchAndCustomerByFamily() {
        final String family = scannerWrapper.getUserInput("Enter the family: ", Function.identity());
        final List<CustomerDto> customers = customerFacade.searchCustomerByFamily(family);
        customers.forEach(System.out::println);
    }

    public void editCustomerById() throws CustomerNotException {
        String id = scannerWrapper.getUserInput("Enter the customer id : ", Function.identity());
        final CustomerDto customerDto = customerFacade.getCustomerById(Integer.valueOf(id));
        System.out.println(customerDto);
        AbstractCustomerUI
                .fromCustomerType(customerDto.getType())
                .editCustomer(customerDto);

        try {
            customerFacade.updateCustomerDto(customerDto);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            editCustomerById();
        }

    }


    public void deletedCustomerById() throws CustomerNotException {
        String id = scannerWrapper.getUserInput("Enter the customer id : ", Function.identity());
        customerFacade.deletedCustomerById(Integer.valueOf(id));
    }




    @Override
    public void close(){
        scannerWrapper.close();
    }




}
