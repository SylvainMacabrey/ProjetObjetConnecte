import java.io.*;
 
public class Position implements Serializable {
 
    private double latitude;
    private double longitude;
    private double altitude;
 
    public Position() {
		this.latitude = 0;
		this.longitude = 0;
		this.altitude = 0;
    }

    public Position(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "latitude = " + latitude +
                ", longitude = " + longitude +
                ", altitude = " + altitude +
                '}';
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    // A COMPLETER
 }
