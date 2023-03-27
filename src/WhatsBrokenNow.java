import service.TestData;

public class WhatsBrokenNow {
    public static void main(String[] args) {

//        TestData.importFakeCustomers();
//        TestData.importFakeRooms();
//        TestData.importFakeReservations();

//        // customers
//        System.out.println("👩🧑👦👶👵 customers test data");
//        System.out.println("🧡 customers collection");
//        System.out.println(CustomerService.customers);
//        System.out.println("💛 single customer lookup");
//        System.out.println(CustomerService.getCustomer("irene@bohemia.com"));
//        System.out.println("💚 all customers class");
//        System.out.println(CustomerService.getAllCustomers());
//        PrettyPrint.printCustomers();
//
//        // rooms
//        System.out.println("\n🏨🔑🛁🚽🛌 rooms test data");
////        IRoom room100 = new model.Room("100", RoomType.SINGLE, 200.00);
////        IRoom room150 = new model.Room("150", RoomType.SINGLE, 200.00);
////        IRoom room200 = new model.Room("200", RoomType.DOUBLE, 400.00);
////        IRoom room300 = new model.Room("300", RoomType.SINGLE, 200.00);
////        IRoom room333 = new model.Room("333", RoomType.SINGLE, 230.00);
////        service.ReservationService.addRoom(room100);
////        service.ReservationService.addRoom(room150);
////        service.ReservationService.addRoom(room200);
////        service.ReservationService.addRoom(room300);
////        service.ReservationService.addRoom(room333);
//        System.out.println("🧡 rooms collection");
//        System.out.println(service.ReservationService.rooms);
//        System.out.println("💛 single room lookup");
//        System.out.println(ReservationService.getARoom("200"));
//        System.out.println("💚 single room pretty print");
////        service.PrettyPrint.printRoom(room200);
//        System.out.println("💚 all rooms pretty print");
//        service.PrettyPrint.printRooms(ReservationService.rooms);
//
//        // reservations
//        System.out.println("\n📞☎💻🖥📒 reservations test data");
//        service.ReservationService.reserveARoom(CustomerService.getCustomer("irene@bohemia.com"),
//                ReservationService.getARoom("100"),
//                LocalDate.of(2024, 01, 01),
//                LocalDate.of(2024, 01, 03));
//        service.ReservationService.reserveARoom(CustomerService.getCustomer("irene@bohemia.com"),
//                ReservationService.getARoom("100"),
//                LocalDate.of(2024, 02, 01),
//                LocalDate.of(2024, 02, 03));
//        service.ReservationService.reserveARoom(CustomerService.getCustomer("irene@bohemia.com"),
//                ReservationService.getARoom("100"),
//                LocalDate.of(2023, 06, 17),
//                LocalDate.of(2023, 06, 20));

//        ReservationService.printAllReservation();
//        Customer customer = api3.HotelResource.getCustomer("irene@bohemia.com");
//        System.out.println(customer);

//        Scanner scanEmail = new Scanner(System.in);
//        System.out.println("Enter the ID (i.e. email) for the customer:");
//        String customerEmail = getValidEmail(scanEmail.nextLine());
//        Customer customer = api.HotelResource.getCustomer(customerEmail);
//        Customer customerTest = CustomerService.getCustomer(customerEmail);
//        System.out.println(customer);
//        System.out.println(customerTest);
//        System.out.println(customers);

        TestData.importFakeRooms();
        TestData.importFakeCustomers();
        TestData.importFakeReservations();

        MainMenu.startMainMenu();


    }
}