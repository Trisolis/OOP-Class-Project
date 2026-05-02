/*
Every time a package's status changes (e.g. Packed -> In Transit), that change could be
recorded as a TrackingEvent with a timestamp and note. This makes the tracking history
easier to access when searching package by ID
*/

public class TrackingEvent {
    // Fields
    final private String trackingId;
    final private Package.Status status;
    final private String timestamp;
    final private String note;

    // Constructor
    public TrackingEvent(String trackingId, Package.Status status, String timestamp, String note) {
        this.trackingId = trackingId;
        this.status = status;
        this.timestamp = timestamp;
        this.note = note;
    }

    // Getters
    public String getTrackingId() { return trackingId; }
    public Package.Status getStatus()     { return status; }
    public String getTimestamp()  { return timestamp; }
    public String getNote()       { return note; }
}