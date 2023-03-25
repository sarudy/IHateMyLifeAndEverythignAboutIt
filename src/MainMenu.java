import api.HotelResource;
import service.CustomerService;
import service.PrettyPrint;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static api.HotelResource.createACustomer;
import static service.CustomerService.getCustomer;
import static service.CustomerService.getValidEmail;

public class MainMenu {
    static DateTimeFormatter dmy = DateTimeFormatter.ofPattern("EEE d MMM yyyy");
    // allowing a more customer friendly date format for output that might be exposed to the guest

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
                    System.out.println("[1] Find and reserve a room             ");
                    startMainMenu();
                    break;
                case "2":
                    System.out.println("[2] See customer reservation(s)         ");
                    startMainMenu();
                    break;
                case "3":
//                    System.out.println("[3] Create an account                   ");
                    createANewAccount();
                    startMainMenu();
                    break;
                case "4":
                    System.out.println("[4] Admin                               " );
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
            System.out.println("Created customer:" + HotelResource.getCustomer(email));
            startMainMenu();
        } else {
            System.out.println(getCustomer(email) + " already exists.");
            System.out.println("Do you want to: " + "\n" +
                    "[1] Return to the main menu" + "\n" +
                    "[2] Create an account using a different email/ID" + "\n" +
                    "═════════════════════════════" + "\n" +
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
                } optionScan.close();
            } catch (Exception e) {
                System.out.println("well that was no good");
            }
        }
        emailScan.close();
        firstScan.close();
        lastScan.close();
    }


}
