import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.PrettyPrint;

import java.util.Scanner;

import static api.AdminResource.*;
import static api.AdminResource.displayAllReservations;
import static service.ReservationService.reservations;

public class AdminMenu {

    public static RoomType pickAType() {
        Scanner scanner = new Scanner(System.in);
        String pickAType = "";
        System.out.println("Is the room a " + "\n" +
                "[1] single or " + "\n" +
                "[2] double?");
        try {
            pickAType = scanner.nextLine().toUpperCase();
            switch (pickAType) {
                case "1":
                    return RoomType.SINGLE;
                case "2":
                    return RoomType.DOUBLE;
                default:
                    System.out.println("Please enter one of the given options:");
                    pickAType();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println("well that was no good");
        }
        return pickAType();
    }

    public static void addARoom() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the room number you would like to create:");
        String number = AdminResource.getValidRoomNumber();
        if (HotelResource.getRoom(number) == null) {
            RoomType type = pickAType();
            System.out.println("Enter the cost per night of the room:");
            Double cost = scanner.nextDouble();
            IRoom aRoom = new Room(number, type, cost);
            AdminResource.addRoom(aRoom);
        } else {
            System.out.println("This room has already been created:");
            System.out.println(HotelResource.getRoom(number));
        }
    }

    public static void startAdminMenu() {
        String menuOptions = "";
        Scanner scanner = new Scanner(System.in);

        printAdminMenu();

        try {
            menuOptions = scanner.nextLine();
            switch (menuOptions) {
                case "1":
                    if (AdminResource.getAllCustomers().isEmpty()) {
                        System.out.println("The customer database is empty. " + "\n" +
                                "You can add customers from the Main Menu (option [5]).");
                    } else {
                    PrettyPrint.printCustomers(); }
                    System.out.println("————————————————————————————————————————");
//                    System.out.println("[1] See all Customers                   ");
                    startAdminMenu();
                    break;
                case "2":
                    if (getAllRooms().isEmpty()) {
                        System.out.println("We have no rooms listed" + "\n" +
                                "You can add rooms via Option [4].");
                    } else {
                        PrettyPrint.printRooms(getAllRooms());
                    }
                    System.out.println("————————————————————————————————————————");
//                    System.out.println("[2] See all rooms                       ");
                    startAdminMenu();
                    break;
                case "3":
                    AdminResource.displayAllReservations();
                    if (reservations.isEmpty()) {
                        System.out.println("We have no reservations listed" + "\n" +
                                "You can add rooms from the Main Menu (option [5]).");
                    }
//                    System.out.println("[3] See all Reservations                ");
                    System.out.println("————————————————————————————————————————");
                    startAdminMenu();
                    break;
                case "4":
                    addARoom();
//                    System.out.println("[4] Add a room                          ");
                    System.out.println("————————————————————————————————————————");
                    startAdminMenu();
                    break;
                case "5":
                    MainMenu.startMainMenu();
                    break;
                default:
                    System.out.println("Please enter one of the given options:");
                    startAdminMenu();
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println("well that was no good");
        }
    }
    public static void printAdminMenu() {
        System.out.println("" +
                "           ADMINISTRATOR MENU           " + "\n" +
                "════════════════════════════════════════" + "\n" +
                "[1] See all Customers                   " + "\n" +
                "[2] See all rooms                       " + "\n" +
                "[3] See all Reservations                " + "\n" +
                "[4] Add a room                          " + "\n" +
                "[5] Back to Main Menu                   " + "\n" +
                "════════════════════════════════════════" + "\n" +
                "Choose an option by number:");
    }

}