import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // TODO: Implement CLI or GUI here for user

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("Courier Service Management System");

        while (running) {
            System.out.println("""
                --- Menu ---
                1. Estimate delivery cost
                2. Add package
                3. Update package status
                4. Display all packages
                5. Search by tracking ID
                6. Remove package
                0. Exit
                """);

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.println("Enter weight (kg)");
                    // double weight = ;
                    // System.out.println("Estimated cost: ___");
                }
                case "2" -> {
                    System.out.print("Description: ");
                    String desc = sc.nextLine().trim();
                    System.out.print("Weight (kg): ");
                    // double weight = ;
                    System.out.print("Destination: ");
                    String dest = sc.nextLine().trim();
                    System.out.print("Mailed date (YYYY-MM-DD): "); // date not set in stone
                    String mailed = sc.nextLine().trim();
                    System.out.print("Expected arrival (YYYY-MM-DD): ");
                    String arrival = sc.nextLine().trim();
                    // Package pkg = .addPackage(desc, weight, dest, mailed, arrival);
                    // System.out.println("Package added. Tracking ID: " + pkg.getTrackingId());
                }
                case "3" -> {
                    System.out.print("Tracking ID: ");
                    String id = sc.nextLine().trim();
                    System.out.print("What should the new status be? (PACKED, IN_TRANSIT, DELVIERED)");
                    // status = ;
                    System.out.print("New expected arrival (YYYY-MM-DD, or press Enter to skip)");
                    String arr = sc.nextLine().trim();
                    System.out.print("Note? ");
                    String note = sc.nextLine().trim();
                    // rest and actually creating a trackingevent
                }
                case "4" -> {
                    // if list is empty, print not found
                    // else print table using printf
                }
                case "5" -> {
                    System.out.print("Tracking ID: ");
                    String id = sc.nextLine().trim();
                    // Package pkg = .findById(id);
                    if (pkg == null) {
                        System.out.println("Package not found.");
                    }
                    else {
                        // print out info here properly formatted. event history needs another if statemnt for if it existss or not
                    }
                }
                case "6" -> {
                    System.out.print("Tracking ID: ");
                    String id = sc.nextLine().trim();
                    // .removeById(id);
                }
                case "0" -> {
                    System.out.println("Thank you for using our service!");
                    running = false;
                }
                default -> {
                    System.out.println("Invalid option, please try again.");
                }
            }
        }
        sc.close();
    }
}