import model.Customer;
import model.RoomType;
import service.CustomerService;

public class WhatsBrokenNow {
    public static void main(String[] args) {
        System.out.println(RoomType.SINGLE);

        // Oh look, fake customers
        model.Customer anonymousCustomer = new Customer("Jon","Doe","toilet@girldeer.net");
        System.out.println(anonymousCustomer);
        service.CustomerService.addCustomer("apples@oranges.net","Froot","Loop");
        service.CustomerService.addCustomer("foo@bar.com","Alice","Walker");
        service.CustomerService.addCustomer("foo1@bar.com","Becky","Smith");
        System.out.println(CustomerService.customers);

    }
}