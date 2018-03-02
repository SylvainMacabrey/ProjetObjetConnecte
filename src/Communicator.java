import java.io.*;
import java.net.*;
import java.util.*;

/*
Cette classe permet de centraliser toutes les méthodes communicantes,
aussi bien à destination du serveur que du Xbee. Comme cela, le client
principal ainsi que le thread client pourront envoyer/recevoir du Xbee/serveur
sans qu'il y ait de problème d'accès concurrent car toutes les méthodes sont
protégées en étant synchronized
 */

class Communicator  {

    BufferedReader br; // reading from xbee file
    FileOutputStream os; // writing to xbee file
    ObjectInputStream ois; // reading from socket
    ObjectOutputStream oos; // writing to socket

    Communicator(String xbeeFile, Socket sock) throws IOException {
		br = new BufferedReader(new FileReader(xbeeFile));
		os = new FileOutputStream(xbeeFile);
		oos = new ObjectOutputStream(sock.getOutputStream());
		oos.flush();
		ois = new ObjectInputStream(sock.getInputStream());
    }

    // read a line of texte from xbee file
    public synchronized String readFromXbee() throws IOException {
		String line = br.readLine();
		return line;
    }

    // write 2 bytes to xbee file
    public synchronized void writeToXbee(byte[] buf) throws IOException {
		os.write(buf,0,2);
    }

    public synchronized Position sendSetPositionRequest(double latitude, double longitude, double altitude) throws IOException, ClassNotFoundException {
		Position pos = null;
		// envoie la requête SETPOSITION AU SERVEUR
        oos.writeInt(1);
        oos.flush();
        oos.writeDouble(latitude);
        oos.writeDouble(longitude);
        oos.writeDouble(altitude);
        oos.flush();
		// reçoit l'ordre pour l'arduino et l'envoie à 'larduino via writeToXbee
        writeToXbee((byte[]) ois.readObject());
		// reçoit la nouvelle position du barycentre -> pos
        pos = (Position) ois.readObject();
		return pos;	
    }

    public synchronized ArrayList<Position> sendGetPositionRequest() throws IOException, ClassNotFoundException {
		ArrayList<Position> pos = null;
		// envoie la requête GETPOSITION au serveur
        oos.writeInt(2);
        oos.flush();
		// reçoit la liste des postions -> pos
        pos = (ArrayList<Position>) ois.readObject();
		return pos;
    }
}
		
