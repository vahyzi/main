package edu.fsu.cs.mobile.bikealert;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.GeoPoint;

public class Rider {
    private String email;
    private Timestamp timestamp;
    private GeoPoint location;

    private Bike bike;

    public Rider(String email, Timestamp timestamp, GeoPoint location, Bike bike) {
        this.email = email;
        this.timestamp = timestamp;
        this.location = location;
        this.bike = bike;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }


    // Testing Rider
    public static Rider generateRider(FirebaseUser user, GeoPoint point, Bike bike) {
        return new Rider(user.getEmail(), Timestamp.now(), new GeoPoint(1, 1), bike);
    }
}
