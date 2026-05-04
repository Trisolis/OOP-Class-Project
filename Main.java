import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PackageRepository repository = new PackageRepository();
        PackageService service = new PackageService(repository);
        CostEstimator estimator = new CostEstimator();
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
                    double weight = Double.parseDouble(sc.nextLine().trim()); // apparently can't use nextDouble() or it causes errors
                    double estimate = estimator.estimate(weight);
                    // System.out.println("Estimated cost: ___");
                }
                case "2" -> {
                    System.out.print("Description: ");
                    String desc = sc.nextLine().trim();
                    System.out.print("Weight (kg): ");
                    double weight = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Destination: ");
                    String dest = sc.nextLine().trim();
                    System.out.print("Mailed date (YYYY-MM-DD): "); 
                    LocalDate mailed = LocalDate.parse(sc.nextLine().trim()); // have to parse strings for LocalDate format
                    System.out.print("Expected arrival (YYYY-MM-DD): ");
                    LocalDate arrival = LocalDate.parse(sc.nextLine().trim());
                    CourierPackage pkg = service.addPackage(desc, weight, dest, mailed, arrival);
                    System.out.println("Package added. Tracking ID: " + pkg.getTrackingId());
                }
                case "3" -> {
                    System.out.print("Tracking ID: ");
                    String id = sc.nextLine().trim();
                    System.out.print("What should the new status be? (PACKED, IN_TRANSIT, DELVIERED)");
                    CourierPackage.Status status = CourierPackage.Status.valueOf(sc.nextLine().trim().toUpperCase()); // valueOf to convert to objec
                    System.out.print("New expected arrival (YYYY-MM-DD, or press Enter to skip)");
                    String arr = sc.nextLine().trim();
                    LocalDate arrivalDate = arr.isEmpty() ? null : LocalDate.parse(arr);
                    System.out.print("Note? ");
                    String note = sc.nextLine().trim();
                    boolean success = service.updateStatus(id, status, arrivalDate, note);
                    System.out.println(success ? "Status updated." : "Package not found.");
                }
                case "4" -> {
                    List<CourierPackage> all = service.getAllPackages();

                    if (all.isEmpty()) {
                        System.out.println("Np packages found.");
                    }
                    else {
                        // Print table w printf
                    }
                }
                case "5" -> {
                    System.out.print("Tracking ID: ");
                    String id = sc.nextLine().trim();
                    CourierPackage pkg = service.findById(id);
                    if (pkg == null) {
                        System.out.println("Package not found.");
                    }
                    else {
                        // print out package info here properly formatted
                        if (pkg.getTrackingHistory().isEmpty()) {
                            System.out.println("No events recorded");
                        }
                        else {
                            // print another table of trackingevents
                        }
                    }
                }
                case "6" -> {
                    System.out.print("Tracking ID: ");
                    String id = sc.nextLine().trim();
                    boolean removed = service.removePackage(id);
                    System.out.println(removed ? "Package removed." : "Package not found");
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