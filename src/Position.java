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

    public String toString() {
        String msg = "latitude: " + latitude + ", "
        		   + "longitude: " + longitude + ", "
        		   + "altitude: " + altitude;
        return msg;
    }

    // A COMPLETER
 }
