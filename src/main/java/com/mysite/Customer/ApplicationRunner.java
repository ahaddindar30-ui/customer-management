package com.mysite.Customer;


import com.mysite.Customer.view.ControlUI;


public class ApplicationRunner {

    public static void main(String[] args) {
        try(ControlUI controlUI = new ControlUI()) {
            controlUI.startMenu();
        }catch (Throwable ex){
            System.out.println("System error!!!");
        }
    }
}
