/*
Every time a package's status changes (e.g. Packed -> In Transit), that change could be
recorded as a TrackingEvent with a timestamp and note. This makes the tracking history
easier to access when searching package by ID
*/

import java.time.LocalDateTime;

public class TrackingEvent {
    // Fields
    final private String trackingId;
    final private CourierPackage.Status status;
    final private LocalDateTime timestamp;
    final private String note;

    // Constructor
    public TrackingEvent(String trackingId, CourierPackage.Status status, LocalDateTime timestamp, String note) {
        this.trackingId = trackingId;
        this.status = status;
        this.timestamp = timestamp;
        this.note = note;
    }

    // Getters
    public String getTrackingId() { return trackingId; }
    public CourierPackage.Status getStatus()     { return status; }
    public LocalDateTime getTimestamp()  { return timestamp; }
    public String getNote()       { return note; }
}