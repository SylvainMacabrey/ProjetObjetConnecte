import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sylva on 26/02/2018.
 */
public class MainServer {

    ObjectOutputStream oos;
    ObjectInputStream ois;
    Communicator comm;
    String xbeeFile;
    ServerSocket conn;
    Socket sock;
    int port;
    int idClient;

    HashMap<Integer, ArrayList<Position>> posClients;

    public MainServer(int port) throws IOException {
        idClient = 1;
        posClients = new HashMap<Integer, ArrayList<Position>>();
        this.port = port;
        conn = new ServerSocket(port);
    }

    public void mainLoop() {
        try {
            while(true) {
                sock = conn.accept();
                ois = new ObjectInputStream(sock.getInputStream());
                oos = new ObjectOutputStream(sock.getOutputStream());
                oos.flush();
                oos.writeInt(idClient);
                oos.flush();
                ArrayList<Position> pos = new ArrayList<Position>();
                posClients.put(idClient,pos);
                requestLoop();
                idClient += 1;
            }
        }
        catch(IOException e) {
            System.out.println("problem with accepting connexions"+e.getMessage());
        }
    }

    public void requestLoop() {

        int numReq = 0;
        int numClient = idClient;
        try {
            while(true) {
                System.out.println(numReq);
                numReq = ois.readInt();
                System.out.println(numReq);
                //numClient = ois.readInt();
                if(numReq == 1) {
                    setPosition(numClient);
                } else if(numReq == 2) {
                    getPosition();
                }
            }
        }
        catch(IOException e) {
            System.out.println("problem with receiving request: "+e.getMessage());
        }
    }

    private void setPosition(int numClient) throws IOException {
        double latitude = ois.readDouble();
        double longitude = ois.readDouble();
        double altitude = ois.readDouble();
        ArrayList<Position> positions = posClients.get(numClient);
        positions.add(new Position(latitude, longitude, altitude));
        Position barycentre = calculBarycentre(altitude);
        Position a = posClients.get(numClient).get(positions.size()-1); // dernière position connu
        Position b = posClients.get(numClient).get(positions.size()-2); // avant dernière position connu
        Segment s1 = new Segment(a, b);
        Segment s2 = new Segment(a, barycentre);
        double angle = calculAngle(s1.calculLongueurSegment(), s2.calculLongueurSegment(), calculScalaire(a, b));
        byte[] ordre = new byte[2];
        if(angle >= -22.5 && angle <= 22.5) {
            ordre[0] = 10;
            ordre[1] = 1;
        } else if (angle > 22.5 && angle <= 90) {
            ordre[0] = 10;
            ordre[1] = 3;
        } else if (angle > -90 && angle <= -22.5) {
            ordre[0] = 10;
            ordre[1] = 4;
        } else if (angle > 90 && angle <= 157.5) {
            ordre[0] = 10;
            ordre[1] = 5;
        } else if (angle > -157.5 && angle <= -90) {
            ordre[0] = 10;
            ordre[1] = 6;
        } else {
            ordre[0] = 10;
            ordre[1] = 2;
        }
        oos.writeObject(ordre);
        oos.writeObject(barycentre);
    }

    private void getPosition() throws IOException {
        ArrayList posClient = new ArrayList();
        for (Map.Entry mapentry : posClients.entrySet()) {
            ArrayList<Position> positions = posClients.get(mapentry.getKey());
            double latitude = positions.get(positions.size()-1).getLatitude();
            double longitude = positions.get(positions.size()-1).getLongitude();
            double altitude = positions.get(positions.size()-1).getAltitude();
            Position p = new Position(latitude, longitude, altitude);
            posClient.add(p);
        }
        oos.writeObject(posClient);
    }

    private Position calculBarycentre(double altitude) {
        double sommeX = 0, sommeY = 0;
        for (Map.Entry mapentry : posClients.entrySet()) {
            ArrayList<Position> positions = posClients.get(mapentry.getKey());
            sommeX += positions.get(positions.size()-1).getLatitude();
            sommeY += positions.get(positions.size()-1).getLongitude();
        }
        int size = posClients.size();
        return new Position(sommeX/size, sommeY/size, altitude);
    }

    private double calculScalaire(Position p1, Position p2) {
        return p1.getLatitude() * p2.getLatitude() + p1.getLongitude() * p2.getLongitude();
    }

    private double calculAngle(double longueur1, double longueur2, double produitScalaire) {
        double cos = produitScalaire / (longueur1 * longueur2);
        return Math.acos(cos);
    }

}
