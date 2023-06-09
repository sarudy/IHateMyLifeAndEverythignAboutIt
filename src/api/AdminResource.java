package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.TreeSet;

import static service.ReservationService.*;

public class AdminResource {
    private static AdminResource INSTANCE;

    private AdminResource() {
    }

    public static AdminResource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdminResource();
        }
        return INSTANCE;
    }

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(IRoom room) {
        ReservationService.addRoom(room);
    }

    public static TreeSet<IRoom> getAllRooms() {
        return rooms;
    }

    public static TreeSet<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations() {
        printAllReservation();
    }

    public static String getValidRoomNumber() {
        return checkRoomNumber();
    }
}
