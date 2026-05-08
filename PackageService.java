/*
Middle layer between UI and database. Interface (CLI/GUI) asks it to do things, and
PackageService figures out logic and talks to repository
*/

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
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
        
        if (pkg == null) {
            return false;
        }

        pkg.setStatus(newStatus);
        pkg.setLastUpdated(LocalDateTime.now());
        if (newExpectedArrivalDate != null) {
            pkg.setExpectedArrivalDate(newExpectedArrivalDate);
        }

        TrackingEvent event = new TrackingEvent(trackingId, newStatus, LocalDateTime.now(), note);
        pkg.addTrackingEvent(event);

        return true;

    }

    public CourierPackage findById(String trackingId) {
        return repository.findById(trackingId);
    }

    public List<CourierPackage> getAllPackages() {
        return repository.findAll();
    }

    public List<CourierPackage> sortAllPackages(String type) {
        List<CourierPackage> list = repository.findAll();
        
        switch (type) {
            case "1":
                // sorts by weight ascending
                list.sort((a, b) -> Double.compare(a.getWeight(), b.getWeight()));
                break;
            case "2":
                list.sort((a, b) -> a.getDestination().compareTo(b.getDestination()));
                break;
            case "3":
                list.sort(Comparator.comparing(CourierPackage::getStatus));
                break;
            case "4":
                list.sort((a, b) -> a.getExpectedArrivalDate().compareTo(b.getExpectedArrivalDate()));
                break;
            case "5":
                list.sort((a, b) -> a.getLastUpdated().compareTo(b.getLastUpdated()));
                break;
            default:
                throw new AssertionError();
        }

        return list;
    }

    public boolean removePackage(String trackingId) {
        return repository.delete(trackingId);
    }
}