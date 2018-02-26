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
                idClient += 1;
                requestLoop();
            }
        }
        catch(IOException e) {
            System.out.println("problem with accepting connexions"+e.getMessage());
        }
    }

    public void requestLoop() {

        int numReq = 0;
        int numClient = 0;
        try {
            while(true) {
                numReq = ois.readInt();
                numClient = ois.readInt();
            }
        }
        catch(IOException e) {
            System.out.println("problem with receiving request: "+e.getMessage());
        }
    }

    private void setPosition(double latitude, double longitude, double altitude, int numClient) {
        ArrayList<Position> positions = posClients.get(numClient);
        positions.add(new Position(latitude, longitude, altitude));
        Position barycentre = calculBarycentre(altitude);
        Segment s1 = new Segment();
        Segment s2 = new Segment();
    }

    private void getPosition() {

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

}
