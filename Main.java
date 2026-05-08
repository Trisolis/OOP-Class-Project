import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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
                0. Exit""");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter weight (kg): ");
                    double weight = Double.parseDouble(sc.nextLine().trim()); // apparently can't use nextDouble() or it causes errors
                    double estimate = estimator.estimate(weight);
                    System.out.printf("Estimated cost: $%.2f\n", estimate);
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
                    if (service.findById(id) == null) {
                        System.out.println("Package not found."); // so it doesn't ask the rest of the questions if nonexistent
                    }
                    else {
                        System.out.print("What should the new status be? (PACKED, IN_TRANSIT, DELIVERED): ");
                        CourierPackage.Status status = CourierPackage.Status.valueOf(sc.nextLine().trim().toUpperCase()); // valueOf to convert to objec
                        System.out.print("New expected arrival (YYYY-MM-DD, or press Enter to skip): ");
                        String arr = sc.nextLine().trim();
                        LocalDate arrivalDate = arr.isEmpty() ? null : LocalDate.parse(arr);
                        System.out.print("Note: ");
                        String note = sc.nextLine().trim();
                        boolean success = service.updateStatus(id, status, arrivalDate, note);
                        System.out.println(success ? "Status updated." : "Package not found.");
                    }
                }
                case "4" -> {
                    List<CourierPackage> all = service.getAllPackages();

                    if (all.isEmpty()) {
                        System.out.println("No packages found.");
                    }
                    else {
                        while (true) {
                            System.out.printf("%-15s %-20s %-15s %-20s %-15s %-15s %-20s%n",
                                    "Tracking ID", "Description", "Weight", "Destination",
                                    "Status", "ETA", "Last Updated");
                            System.out.println("-".repeat(121));
                            for (CourierPackage p : all) {
                                System.out.printf("%-15s %-20s %-15.2f %-20s %-15s %-15s %-20s%n",
                                        p.getTrackingId(), p.getDescription(), p.getWeight(),
                                        p.getDestination(), p.getStatus(), 
                                        p.getExpectedArrivalDate(), p.getLastUpdated());
                            }
                            System.out.println();
                            System.out.println("Do you want to sort this table by columns? Select an option below.");
                            System.out.println("""
                                    --- Options ---
                                    1. Weight
                                    2. Destination
                                    3. Status
                                    4. ETA
                                    5. Last Updated
                                    0. Return to Main Interface""");
                            String option = sc.nextLine().trim();
                            if (option.equals("0")) break;
                            all = service.sortAllPackages(option);
                        }
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
                        System.out.println("\n--- Package Details ---");
                        System.out.println("Tracking ID:  " + pkg.getTrackingId());
                        System.out.println("Description:  " + pkg.getDescription());
                        System.out.println("Weight:       " + pkg.getWeight() + " kg");
                        System.out.println("Destination:  " + pkg.getDestination());
                        System.out.println("Mailed:       " + pkg.getMailedDate());
                        System.out.println("ETA:          " + pkg.getExpectedArrivalDate());
                        System.out.println("Status:       " + pkg.getStatus());
                        System.out.println("Last Updated: " + pkg.getLastUpdated());
                        System.out.println("\n--- Tracking History ---");
                        if (pkg.getTrackingHistory().isEmpty()) {
                            System.out.println("No events recorded");
                        }
                        else {
                            for (TrackingEvent e : pkg.getTrackingHistory()) {
                                System.out.printf("%s | %s | %s%n",
                                        e.getTimestamp(), e.getStatus(), e.getNote());
                            }
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