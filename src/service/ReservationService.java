package service;

import model.Customer;
import model.IRoom;

import java.util.TreeSet;

public class ReservationService {
    public static final TreeSet<IRoom> rooms = new TreeSet<>();

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
}
