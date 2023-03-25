package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;
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

    public static IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (roomId == room.getRoomNumber()) {
                return room;
            }
        }
        return null;
    }

    public static Reservation reserveARoom(final Customer customer, final IRoom room,
                                           final LocalDate checkInDate, final LocalDate checkOutDate) {
        final Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    private static boolean isBooked(final String roomNumber, final LocalDate checkInDate, final LocalDate checkOutDate) {
        for (Reservation reservation : reservations) {
            if (roomNumber == reservation.getRoom().getRoomNumber() &&
                    checkInDate.isBefore(reservation.getCheckOutDate()) &&
                    checkOutDate.isAfter(reservation.getCheckInDate())) {
                return true;
            }
        }
        return false;
    }

    public static Collection<IRoom> findrooms(LocalDate checkInDate, LocalDate checkOutDate) {
        //find all rooms not booked for the given range//
//        TreeSet<IRoom> booked = new TreeSet<>();
        TreeSet<IRoom> open = new TreeSet<>();
        for (IRoom room : rooms) {
            if (isBooked(room.getRoomNumber(), checkInDate, checkOutDate)) {
//                booked.add(room);
            } else {
                open.add(room);
            }
        }
        return open;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        TreeSet<Reservation> reservedByCustomer = new TreeSet<>();
        for (Reservation reservation : reservations) {
            if (customer == reservation.getCustomer()) {
                reservedByCustomer.add(reservation);
            }
        }
        return reservedByCustomer;
    }

    public static void printAllReservation() {
        if (reservations.isEmpty()) {
            System.out.println("No active reservations found.");
        } else {
            PrettyPrint.printReservations(reservations);
        }
    }

    public static boolean isADate(String date) {
        try {
            LocalDate test = LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate getValiDate(String date) {
        {
            if (isADate(date)) {
            } else {
                boolean realDate = false;
                while (!realDate) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please enter a valid date in the format yyyy-mm-dd");
                    String tryAgain = scanner.nextLine();
                    realDate = isADate(tryAgain);
                    if (isADate(tryAgain)) {
                        scanner.close();
                        return LocalDate.parse(tryAgain);
                    }
                }
            }
        }
        return LocalDate.parse(date);
    }


    public static LocalDate noYesterdays(LocalDate CheckIn) {
        if (!CheckIn.isBefore(LocalDate.now())) ;
        {
            while (CheckIn.isBefore(LocalDate.now())) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("We cannot backdate reservations." + "\n" +
                        "Please provide a check in date today (" +
                        LocalDate.now() + ") or later: ");
                LocalDate tryAgain = getValiDate(scanner.nextLine());
                if (!CheckIn.isAfter(LocalDate.now())) {
                    scanner.close();
                    return tryAgain;
                }
            }
            return CheckIn;
        }
    }

    public static LocalDate linearTimePlease(LocalDate CheckIn, LocalDate CheckOut) {
        if (CheckOut.isAfter(CheckIn)) ;
        {
            while (CheckOut.isBefore(CheckIn)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Visits must be at least one night long." + "\n" +
                        "Please provide a check out date at least one day after check in: ");
                LocalDate tryAgain = getValiDate(scanner.nextLine());
                if (CheckOut.isAfter(CheckIn)) {
                    return tryAgain;
                }
            }
            return CheckOut;
        }

    }

    public static String checkRoomNumber() {
        Scanner scanner = new Scanner(System.in);
        boolean goodNumber = false;
        // user input is guilty until proven innocent
        while (!goodNumber) {
            System.out.println("Enter a three digit room number:");
            // most hotels that are large enough not to have named rooms start first floor rooms at 100, second floor rooms
            // at 200 and so forth.  Though no upper bound was given, a hotel cannot have infinite height. For the sake of
            // simplicity, then, this is hotel has nine floors.
            String roomNumber = scanner.nextLine();
            try {
                int isANumber = Integer.parseInt(roomNumber);
                if (isANumber > 99 && isANumber < 1000) {
                    return roomNumber;
                } else {
                    System.out.println("The room number should be between 100 and 999.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Room numbers should be numbers only.");
                checkRoomNumber();
            }
        }
        return null;
    }

}
