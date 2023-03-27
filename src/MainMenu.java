import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.PrettyPrint;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.jar.JarOutputStream;

import static api.HotelResource.createACustomer;
import static service.CustomerService.getCustomer;
import static service.CustomerService.getValidEmail;
import static service.ReservationService.*;

public class MainMenu {
    static DateTimeFormatter dmy = DateTimeFormatter.ofPattern("EEE d MMM yyyy");
    // allowing a more customer friendly date format for output that might be exposed to the guest


    private static void actuallyBookTheRoom(Customer customer, TreeSet<IRoom> availableRooms,
                                            LocalDate checkIn, LocalDate checkOut) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which room would " + customer.getFirstName() + " like to reserve?");
        String roomNumber = scanner.nextLine();
        IRoom roomChoice = HotelResource.getRoom(roomNumber);
        if (availableRooms.contains(roomChoice)) {
            Reservation reservation = reserveARoom(customer, roomChoice, checkIn, checkOut);
            System.out.println("You have reserved:");
            PrettyPrint.printReservation(reservation);
            System.out.println("————————————————————————————————————————");
        } else {
            System.out.println("That room is not available for the time frame." +
                    customer.getFirstName() + " will need to choose from one of the following:");
            PrettyPrint.printRooms(availableRooms);
            System.out.println("————————————————————————————————————————");
            actuallyBookTheRoom(customer, availableRooms, checkIn, checkOut);
        }
    }

    public static void findAndReserveARoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID (i.e. email) for the customer making a reservation:");
        String customerEmail = getValidEmail(scanner.nextLine());
        Customer customer = HotelResource.getCustomer(customerEmail);
        System.out.println(customer);

        if (customer != null) {
            System.out.println("What date would " + customer.getFirstName() + " like to check in?" + "\n" +
                    "Enter date in yyyy-mm-dd format:");
            // I'd like to say I am using yyyy-mm-dd here because it reduces the chance of an error
            // via the dd/mm/yyyy and mm/dd/yyyy date formats, but it's really because it parses
            // so much more smoothly.

            LocalDate checkIn = noYesterdays(getValiDate(scanner.nextLine()));
            System.out.println("check In date: " + checkIn);

            System.out.println("What date would " + customer.getFirstName() + " like to check out?");
            LocalDate checkOut = linearTimePlease(checkIn, getValiDate(scanner.nextLine()));

            TreeSet<IRoom> availableRooms = HotelResource.findARoom(checkIn, checkOut);
            System.out.println(availableRooms);

            if (!availableRooms.isEmpty()) {
                System.out.println("These rooms are available for a stay from " +
                        dmy.format(checkIn) + " to " +
                        dmy.format(checkOut));
                PrettyPrint.printRooms(availableRooms);
                System.out.println("————————————————————————————————————————");
                actuallyBookTheRoom(customer, availableRooms, checkIn, checkOut);
            } else {
                System.out.println("We are sorry.  There are no available rooms for a stay from " +
                        dmy.format(checkIn) + " to " +
                        dmy.format(checkOut));
                System.out.println("\nWe'll check the following week for alternatives.");
                System.out.println("————————————————————————————————————————");
                TreeSet<IRoom> alternateRooms = HotelResource.findARoom(
                        checkIn.plusWeeks(1),
                        checkOut.plusWeeks(1));
                System.out.println("These rooms are available for a stay from " +
                        dmy.format(checkIn.plusWeeks(1)) + " to " +
                        dmy.format(checkOut.plusWeeks(1)));
                PrettyPrint.printRooms(alternateRooms);
                System.out.println("————————————————————————————————————————");
                actuallyBookTheRoom(customer, alternateRooms, checkIn.plusWeeks(1), checkOut.plusWeeks(1));
                if (!alternateRooms.isEmpty()) {} else {
                    System.out.println("We are sorry.  There are no rooms for either time frame.");
                    System.out.println("════════════════════════════════════════");
                    startMainMenu();
                }
            }


        }
    }

    public static void seeCustomerReservation() {
        Scanner scanEmail = new Scanner(System.in);
        System.out.println("Enter the ID (i.e. email) for the customer:");
        String customerEmail = getValidEmail(scanEmail.nextLine());
        Customer customer = HotelResource.getCustomer(customerEmail);

        if (customer != null) {
            TreeSet<Reservation> customerReservations = HotelResource.getCustomerReservations(customerEmail);

            if (!customerReservations.isEmpty()) {
                PrettyPrint.printReservations(customerReservations);
                System.out.println("————————————————————————————————————————");
                startMainMenu();
            } else {
                System.out.println("We have no reservations on file for ");
                PrettyPrint.printCustomer(customer);
                System.out.println("————————————————————————————————————————");
                startMainMenu();
            }

        } else {
            System.out.println("We have no customer with the ID: " + customerEmail + ".");
            System.out.println("Do you want to:              " + "\n" +
                    "[1] Try entering an alternate email     " + "\n" +
                    "[2] Return to the Main Menu             " + "\n" +
                    "════════════════════════════════════════" + "\n" +
                    "Choose an option by number:");
            try {
                Scanner optionScan = new Scanner(System.in);
                String menuOptions = "";
                menuOptions = optionScan.nextLine();
                switch (menuOptions) {
                    case "1":
                        seeCustomerReservation();
                        break;
                    case "2":
                        startMainMenu();
                        break;
                    default:
                        System.out.println("Please enter one of the given options:");
                        startMainMenu();
                }
            } catch (Exception e) {
                System.out.println("well that was no good");
            }
        }
    }


    public static void createANewAccount() {
        Scanner emailScan = new Scanner(System.in);
        Scanner firstScan = new Scanner(System.in);
        Scanner lastScan = new Scanner(System.in);

        System.out.println("Enter the customer's email (this will also serve as the customer ID:");
        String email = getValidEmail(emailScan.nextLine());

        System.out.println("Enter the customer's first name:");
        String first = firstScan.nextLine();

        System.out.println("Enter the customer's last name:");
        String last = lastScan.nextLine();

        if (getCustomer(email) == null) {
            createACustomer(email, first, last);
            System.out.println("Created customer:");
            PrettyPrint.printCustomer(getCustomer(email));
            System.out.println("————————————————————————————————————————");
            startMainMenu();
        } else {
            System.out.println(getCustomer(email) + " already exists.");
            System.out.println("Do you want to: " + "\n" +
                    "[1] Return to the main menu" + "\n" +
                    "[2] Create a new account using a different email/ID" + "\n" +
                    "════════════════════════════════════════" + "\n" +
                    "Choose an option by number:");
            try {
                Scanner optionScan = new Scanner(System.in);
                String menuOptions = "";
                menuOptions = optionScan.nextLine();
                switch (menuOptions) {
                    case "1":
                        startMainMenu();
                        break;
                    case "2":
                        createANewAccount();
                        break;
                    default:
                        System.out.println("Please enter one of the given options:");
                        startMainMenu();
                }
                optionScan.close();
            } catch (Exception e) {
                System.out.println("well that was no good");
            }
        }
        emailScan.close();
        firstScan.close();
        lastScan.close();
    }


    public static void printMainMenu() {
        System.out.println("" +
                "       CUSTOMER RESERVATION MENU        " + "\n" +
                "════════════════════════════════════════" + "\n" +
                "[1] Find and reserve a room             " + "\n" +
                "[2] See customer reservation(s)         " + "\n" +
                "[3] Create an account                   " + "\n" +
                "[4] Admin                               " + "\n" +
                "[5] Exit                                " + "\n" +
                "════════════════════════════════════════" + "\n" +
                "Choose an option by number:");
        // I changed the numbering style to brackets because I think they look better.
        // I changed choice 2 to "see customer" because given the other options and other menu, it makes
        // no sense that a customer would be using this directly.  This is clearly an agent screen.
    }

    public static void startMainMenu() {
        String menuOptions = "";
        Scanner scanner = new Scanner(System.in);

        printMainMenu();

        try {
            menuOptions = scanner.nextLine();
            switch (menuOptions) {
                case "1":
//                    System.out.println("[1] Find and reserve a room             ");
                    findAndReserveARoom();
                    startMainMenu();
                    break;
                case "2":
//                    System.out.println("[2] See customer reservation(s)         ");
                    seeCustomerReservation();
                    startMainMenu();
                    break;
                case "3":
//                    System.out.println("[3] Create an account                   ");
                    createANewAccount();
                    startMainMenu();
                    break;
                case "4":
//                    System.out.println("[4] Admin                               ");
                    AdminMenu.startAdminMenu();
                    break;
                case "5":
//                    System.out.println("[5] Exit                                ");
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Please enter one of the given options:");
                    startMainMenu();
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println("well that was no good");
        }
    }
}
