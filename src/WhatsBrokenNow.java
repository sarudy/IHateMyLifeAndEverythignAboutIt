import model.Customer;
import model.RoomType;
import service.CustomerService;

public class WhatsBrokenNow {
    public static void main(String[] args) {

        // Oh look, fake customers
        service.CustomerService.addCustomer("toilet@girldeer.net","Jon","Doe");
        service.CustomerService.addCustomer("apples@oranges.net","Froot","Loop");
        service.CustomerService.addCustomer("irene@bohemia.com","Irene","Adler");
        service.CustomerService.addCustomer("cptjack@badwolf.com","Jack","Harkness");
        System.out.println(CustomerService.customers);

        System.out.println(CustomerService.getCustomer("irene@bohemia.com"));

        System.out.println(CustomerService.getAllCustomers());

    }
}