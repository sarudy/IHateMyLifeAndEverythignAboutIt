package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.time.LocalDate;
import java.util.TreeSet;

public class HotelResource {
    private static HotelResource INSTANCE;

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HotelResource();
        }
        return INSTANCE;
    }

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) {
        CustomerService.addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom(String roomNumber) {
        return ReservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String email, IRoom room, LocalDate checkIn, LocalDate checkOut) {
        return ReservationService.reserveARoom(CustomerService.getCustomer(email), room, checkIn, checkOut);
    }

    public static TreeSet<Reservation> getCustomerReservations(String email) {
        return ReservationService.getCustomersReservation(getCustomer(email));
    }

    public static TreeSet<IRoom> findARoom(LocalDate checkIn, LocalDate checkOut) {
        return ReservationService.findrooms(checkIn, checkOut);
    }

}
