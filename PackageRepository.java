/*
The only file that talks directly to the database. Everything else goes through this when
it wants to save or retrieve data (could make a Singleton?)
*/

import java.util.List;

public class PackageRepository {
    // TODO: Set up db connection here

    public void save(CourierPackage pkg) {
        // TODO: Insert a package into the db using its fields
    }

    public CourierPackage findById(String trackingId) {
        // TODO: Find and return package related to its trackingId, return null if not found
        return null;
    }

    public List<CourierPackage> findAll() {
        // TODO: Return all packages in the db as a list of CourierPackage objects
        return null;
    }

    public void update(CourierPackage pkg) {
        // TODO: Update the matching package in the db with its updated fields, and insert new TrackingEvent for status change
    }

    public boolean delete(String trackingId) {
        // TODO: Delete package related to its trackingId, and return false if nothing is deleted (i.e. ID doesn't exist)
        return false;
    }
}