package com.mysite.Customer.util;
import java.util.Scanner;
import java.util.function.Function;


public class ScannerWrapper implements AutoCloseable {

    private static final Scanner scanner;
    private static final ScannerWrapper INSTANCE;
    public static ScannerWrapper getInstance(){
        return INSTANCE;
    }
    static {
        scanner = new Scanner(System.in);
        INSTANCE = new ScannerWrapper();
    }
    private ScannerWrapper(){

    }

    public <T> T getUserInput(String massage , Function<String , T>converter) {
        System.out.println(massage);
        try {
            return converter.apply(scanner.nextLine());
        }catch (Exception e){
            System.out.println("wrong input!");
            return getUserInput(massage , converter);
        }

    }



    @Override
    public void close() {
        scanner.close();

    }


}
