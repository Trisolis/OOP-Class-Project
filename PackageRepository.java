/*
The only file that talks directly to the database. Everything else goes through this when
it wants to save or retrieve data (could make a Singleton?)
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageRepository {
   
    // key: UUID, val: CourierPackage object
    private Map<String, CourierPackage> map = new HashMap<>();

    public void save(CourierPackage pkg) {
        map.put(pkg.getTrackingId(), pkg);
    }

    public CourierPackage findById(String trackingId) {
        // TODO: Find and return package related to its trackingId, return null if not found
        if (map.containsKey(trackingId)) return map.get(trackingId);
        return null;
    }

    public List<CourierPackage> findAll() {
        // TODO: Return all packages in the db as a list of CourierPackage objects
        return new ArrayList<>(map.values());
    }

    public boolean delete(String trackingId) {
        // TODO: Delete package related to its trackingId, and return false if nothing is deleted (i.e. ID doesn't exist)
        if (map.containsKey(trackingId)) {
            map.remove(trackingId);
            return true;
        }
        else return false;
    }
}