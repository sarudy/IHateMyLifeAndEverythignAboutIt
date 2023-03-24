package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.time.LocalDate;
import java.util.TreeSet;

public class ReservationService {
    public static final TreeSet<IRoom> rooms = new TreeSet<>();
    public static final TreeSet<Reservation> reservations = new TreeSet<>();
    private static ReservationService INSTANCE;

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationService();
        }
        return INSTANCE;
    }
    public static void addRoom(IRoom room) {
        rooms.add(room);
    }
    public static Reservation reserveARoom(final Customer customer, final IRoom room,
                                           final LocalDate checkInDate, final LocalDate checkOutDate) {
        final Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }
}
