package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    public static final TreeSet<Customer> customers = new TreeSet<>();
    private static CustomerService INSTANCE;

    public static CustomerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerService();
        }
        return INSTANCE;
    }


    public static String getValidEmail(String email) {
        if (!Customer.isValidEmail(email)) {
            boolean goodEmail = false;
            while (!goodEmail) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Invalid email format.  Please enter the email again:");
                String tryAgain = scanner.nextLine();
                goodEmail = Customer.isValidEmail(tryAgain);
                if (Customer.isValidEmail(tryAgain)) {
                    scanner.close();
                    return tryAgain.toLowerCase();
                }
            }
        }
        return email.toLowerCase();
        // I was thinking as long as I was in here I should make all the emails lowercase to save lookup issues later.
    }
    public static void addCustomer(String email, String firstName, String lastName) {
        try {
            Customer customer = new Customer(firstName, lastName, email);
            customers.add(customer);
        } catch (IllegalArgumentException e) {
            System.out.println("Please use a valid email address.");
        }
    }
}
