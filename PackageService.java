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
        repository.save(pkg);
        return pkg;

    }

    public boolean updateStatus(String trackingId, CourierPackage.Status newStatus, 
                                LocalDate newExpectedArrivalDate, String note) {
        CourierPackage pkg = repository.findById(trackingId);
        if (pkg == null) return false;

        pkg.setStatus(newStatus);
        pkg.setLastUpdated(LocalDateTime.now());
        if (newExpectedArrivalDate != null) {
            pkg.setExpectedArrivalDate(newExpectedArrivalDate);
        }

        TrackingEvent event = new TrackingEvent(trackingId, newStatus, LocalDateTime.now(), note);
        pkg.addTrackingEvent(event);

        repository.update(pkg);
        return true;

    }

    public CourierPackage findById(String trackingId) {
        return repository.findById(trackingId);
    }

    public List<CourierPackage> getAllPackages() {
        return repository.findAll();
    }

    public boolean removePackage(String trackingId) {
        return repository.delete(trackingId);
    }
}