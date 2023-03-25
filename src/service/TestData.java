package service;

import model.IRoom;
import model.RoomType;

import java.time.LocalDate;

public class TestData {

    private static TestData INSTANCE;

    private TestData() {
    }

    public static TestData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestData();
        }
        return INSTANCE;
    }

    public static void importFakeCustomers() {
        service.CustomerService.addCustomer("toilet@girldeer.net", "Jon", "Doe");
        service.CustomerService.addCustomer("apples@oranges.net", "Froot", "Loop");
        service.CustomerService.addCustomer("irene@bohemia.com", "Irene", "Adler");
        service.CustomerService.addCustomer("cptjack@badwolf.com", "Jack", "Harkness");
        service.CustomerService.addCustomer("greensleeves@headless.com", "Ann", "Boleyn");
    }

    public static void importFakeRooms() {
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
    }

    public static void importFakeReservations() {
        service.ReservationService.reserveARoom(CustomerService.getCustomer("irene@bohemia.com"),
                ReservationService.getARoom("100"),
                LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 01, 03));
        service.ReservationService.reserveARoom(CustomerService.getCustomer("irene@bohemia.com"),
                ReservationService.getARoom("100"),
                LocalDate.of(2024, 02, 01),
                LocalDate.of(2024, 02, 03));
        service.ReservationService.reserveARoom(CustomerService.getCustomer("irene@bohemia.com"),
                ReservationService.getARoom("100"),
                LocalDate.of(2023, 06, 17),
                LocalDate.of(2023, 06, 20));
        service.ReservationService.reserveARoom(CustomerService.getCustomer("apples@oranges.net"),
                ReservationService.getARoom("200"),
                LocalDate.of(2025, 01, 01),
                LocalDate.of(2025, 01, 04));
        service.ReservationService.reserveARoom(CustomerService.getCustomer("cptjack@badwolf.com"),
                ReservationService.getARoom("200"),
                LocalDate.of(2023, 06, 17),
                LocalDate.of(2023, 06, 20));
    }

}
