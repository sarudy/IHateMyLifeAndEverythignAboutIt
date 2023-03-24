import model.Customer;
import model.RoomType;
import service.CustomerService;

public class WhatsBrokenNow {
    public static void main(String[] args) {

        // Oh look, fake customers
        service.CustomerService.addCustomer("toilet@girldeer.net","Jon","Doe");
        service.CustomerService.addCustomer("apples@oranges.net","Froot","Loop");
        service.CustomerService.addCustomer("foo@bar.com","Alice","Walker");
        service.CustomerService.addCustomer("foo1@bar.com","Becky","Smith");
        System.out.println(CustomerService.customers);

    }
}