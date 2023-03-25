package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import static service.CustomerService.addCustomer;
import static service.CustomerService.customers;
import static service.ReservationService.*;

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
        return getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) {
        addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom(String roomNumber) {
       return getARoom(roomNumber);
    }

    public static Reservation bookARoom(String email, IRoom room, LocalDate checkIn, LocalDate checkOut) {
        return reserveARoom(getCustomer(email), room, checkIn, checkOut);
    }

    public static Collection<Reservation> getCustomerReservations(String email) {
        return getCustomersReservation(getCustomer(email));
    }

    public static Map<String, IRoom> findARoom(LocalDate checkIn, LocalDate checkOut) {
        return findARoom(checkIn, checkOut);
    }

}
