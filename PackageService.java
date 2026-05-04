/*
Middle layer between UI and database. Interface (CLI/GUI) asks it to do things, and
PackageService figures out logic and talks to repository
*/

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID; 

public class PackageService {
    // Fields
    private final PackageRepository repository;

    // Constructor
    public PackageService(PackageRepository repository) {
        this.repository = repository;
    } 

    // Functions
    public CourierPackage addPackage(String description, double weight, String destination, 
                              LocalDate mailedDate, LocalDate expectedArrivalDate) {
        String trackingId = "PKG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(); // like this style of ID to use
        CourierPackage pkg = new CourierPackage(trackingId, description, weight, destination, mailedDate, expectedArrivalDate);
        // save pkg to db
        return pkg;

    }

    public boolean updateStatus(String trackingId, CourierPackage.Status newStatus, 
                                LocalDate newExpectedArrivalDate, String note) {
        // find pkg through repository
        if (pkg == null) return false;

        pkg.setStatus(newStatus);
        pkg.setLastUpdated(LocalDateTime.now());
        if (newExpectedArrivalDate != null) {
            pkg.setExpectedArrivalDate(newExpectedArrivalDate);
        }

        TrackingEvent event = new TrackingEvent(trackingId, newStatus, LocalDateTime.now(), note);
        pkg.addTrackingEvent(event);

        // update pkg in repository
        return true;

    }

    public CourierPackage findById() {
        // should call to repository function
    }

    public List<CourierPackage> getAllPackages() {
        // should call to repository function
    }

    public boolean removePackage() {
        // should call to repository function
    }
}