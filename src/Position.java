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

    @Override
    public String toString() {
        return "Position{" +
                "latitude = " + latitude +
                ", longitude = " + longitude +
                ", altitude = " + altitude +
                '}';
    }

    // A COMPLETER
 }
