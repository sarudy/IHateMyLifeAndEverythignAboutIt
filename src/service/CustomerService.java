package service;

import model.Customer;

import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

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
                Scanner scanEmail = new Scanner(System.in);
                System.out.println("Invalid email format.  Please enter the email again:");
                String tryAgain = scanEmail.nextLine();
                goodEmail = Customer.isValidEmail(tryAgain);
                if (Customer.isValidEmail(tryAgain)) {
                    return tryAgain.toLowerCase();
                } // scanEmail.close();
            }
        }
        return email.toLowerCase();
        // I was thinking as long as I was in here I should make all the emails lowercase to save lookup issues later.
    }

    public static void addCustomer(String email, String firstName, String lastName) {
        try {
            Customer newCustomer = new Customer(email, firstName, lastName);
            customers.add(newCustomer);
        } catch (IllegalArgumentException e) {
            System.out.println("Please use a valid email address. ");
        }
    }

    public static Customer getCustomer(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    public static TreeSet<Customer> getAllCustomers() {
        return customers;
    }
}
