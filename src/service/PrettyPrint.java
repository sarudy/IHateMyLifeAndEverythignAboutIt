package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TreeSet;


public class PrettyPrint {

    static NumberFormat formatter = NumberFormat.getCurrencyInstance();
    private static PrettyPrint INSTANCE;

    private PrettyPrint() {
    }

    public static PrettyPrint getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PrettyPrint();
        }
        return INSTANCE;
    }

    public static void printCustomer(Customer customer) {
        System.out.println(customer.getFirstName() + " " + customer.getLastName() + " (" + customer.getEmail() + ")");
    }

    public static void printCustomers() {
        System.out.println("Customer List:");
        for (Customer customer : CustomerService.customers) {
            printCustomer(customer);
        }
    }

    public static void printRoom(IRoom thisRoom) {
        System.out.println("Room " + thisRoom.getRoomNumber() + " is available for " +
                thisRoom.getRoomType() + " occupancy at " +
                formatter.format(thisRoom.getRoomPrice()) + " per night");
    }

    public static void printRooms(TreeSet<IRoom> theseRooms) {
        for (IRoom thisRoom : theseRooms) {
            printRoom(thisRoom);
        }
    }

    public static void printReservation(Reservation reservation) {
        DateTimeFormatter dmy = DateTimeFormatter.ofPattern("d MMM YYYY");
        System.out.println(reservation.getCustomer() + "\n" +
                reservation.getRoom() + "\n" +
                "Dates: " + dmy.format(reservation.getCheckInDate()) + " to " + dmy.format(reservation.getCheckOutDate()) + "\n" +
                "Total Cost: " +
                formatter.format((ChronoUnit.DAYS.between(reservation.getCheckInDate(),
                        reservation.getCheckOutDate())) * reservation.getRoom().getRoomPrice()) + "\n"
        );
    }

    public static void printReservations(TreeSet<Reservation> theseReservations) {
        for (Reservation thisReservation : theseReservations) {
            printReservation(thisReservation);
        }
    }
}
