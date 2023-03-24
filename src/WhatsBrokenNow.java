import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;

public class WhatsBrokenNow {
    public static void main(String[] args) {

        // customers
        System.out.println("👩🧑👦👶👵 customers test data");
        service.CustomerService.addCustomer("toilet@girldeer.net", "Jon", "Doe");
        service.CustomerService.addCustomer("apples@oranges.net", "Froot", "Loop");
        service.CustomerService.addCustomer("irene@bohemia.com", "Irene", "Adler");
        service.CustomerService.addCustomer("cptjack@badwolf.com", "Jack", "Harkness");
        service.CustomerService.addCustomer("greensleeves@headless.com", "Ann", "Boleyn");
        System.out.println("🧡 customers collection");
        System.out.println(CustomerService.customers);
        System.out.println("💛 single customer lookup");
        System.out.println(CustomerService.getCustomer("irene@bohemia.com"));
        System.out.println("💚 all customers class");
        System.out.println(CustomerService.getAllCustomers());

        // rooms
        System.out.println("\n🏨🔑🛁🚽🛌 rooms test data");
        IRoom room100 = new model.Room("100", RoomType.SINGLE, 200.00);
        IRoom room150 = new model.Room("150", RoomType.SINGLE, 200.00);
        IRoom room200 = new model.Room("200", RoomType.DOUBLE, 400.00);
        IRoom room300 = new model.Room("300", RoomType.SINGLE, 200.00);
        IRoom room333 = new model.Room("333", RoomType.SINGLE, 230.00);
        service.ReservationService.addRoom(room100);
        service.ReservationService.addRoom(room150);
        service.ReservationService.addRoom(room200);
        service.ReservationService.addRoom(room300);
        service.ReservationService.addRoom(room333);
        System.out.println("🧡 rooms collection");
        System.out.println(service.ReservationService.rooms);

        // reservations
        System.out.println("\n📞☎💻🖥📒 reservations test data");
    }
}