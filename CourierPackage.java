/*
Represents a single package in the system
Holds details about one shipment (fields below)
*/

import java.time.LocalDate; // for standalone dates
import java.time.LocalDateTime; // for dates and times
import java.util.ArrayList;
import java.util.List;

public class CourierPackage {
    // enum bc status options should be grouped together and there should only be these 3
    public enum Status {
        PACKED,
        IN_TRANSIT,
        DELIVERED
    }

    // Fields
    final private String trackingId;
    final private String description;
    final private double weight;
    final private String destination;
    final private LocalDate mailedDate; 
    private LocalDate expectedArrivalDate;
    private Status status;
    private LocalDateTime lastUpdated;
    final private List<TrackingEvent> trackingHistory = new ArrayList<>();

    // Constructor
    public CourierPackage(String trackingId, String description, double weight, String destination,
                   LocalDate mailedDate, LocalDate expectedArrivalDate) {
        this.trackingId = trackingId;
        this.description = description;
        this.weight = weight;
        this.destination = destination;
        this.mailedDate = mailedDate;
        this.expectedArrivalDate = expectedArrivalDate;
        this.status = Status.PACKED;
        this.lastUpdated = LocalDateTime.now();
    }

    // Getters
    public String getTrackingId()                      { return trackingId; }
    public String getDescription()                     { return description; }
    public double getWeight()                          { return weight; }
    public String getDestination()                     { return destination; }
    public LocalDate getMailedDate()                   { return mailedDate; }
    public LocalDate getExpectedArrivalDate()          { return expectedArrivalDate; }
    public Status getStatus()                          { return status; }
    public LocalDateTime getLastUpdated()              { return lastUpdated; }  
    public List<TrackingEvent> getTrackingHistory()    { return trackingHistory; }

    // Setters
    public void setStatus (Status status) {
        this.status = status;
    }

    public void setExpectedArrivalDate(LocalDate expectedArrivalDate) {
        this.expectedArrivalDate = expectedArrivalDate;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void addTrackingEvent(TrackingEvent event) {
        trackingHistory.add(event);
    }
}