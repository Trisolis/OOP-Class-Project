/*
Represents a single package in the system
Holds details about one shipment (fields below)
*/

public class Package {
    // enum bc status options should be grouped together and there should only be these 3
    public enum Status {
        PACKED,
        IN_TRANSIT,
        DELIVERED
    }

    // Fields
    private String trackingId;
    private String description;
    private double weight;
    private String destination;
    private String mailedDate; // could be in different format
    private String expectedArrivalDate;
    private Status status;
    private String lastUpdated;

    // Constructor
    public Package(String trackingId, String description, double weight, String destination,
                   String mailedDate, String expectedArrivalDate) {
        this.trackingId = trackingId;
        this.description = description;
        this.weight = weight;
        this.destination = destination;
        this.mailedDate = mailedDate;
        this.expectedArrivalDate = expectedArrivalDate;
        this.status = Status.PACKED;
        this.lastUpdated = mailedDate;
    }

    // Getters
    public String getTrackingId()           { return trackingId; }
    public String getDescription()          { return description; }
    public double getWeight()               { return weight; }
    public String getDestination()          { return destination; }
    public String getMailedDate()           { return mailedDate; }
    public String getExpectedArrivalDate()  { return expectedArrivalDate; }
    public Status getStatus()               { return status; }
    public String getLastUpdated()          { return lastUpdated; }   

    // Setters
    public void setStatus (Status status) {
        this.status = status;
    }

    public void setExpectedArrivalDate(String expectedArrivalDate) {
        this.expectedArrivalDate = expectedArrivalDate;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}