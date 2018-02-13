import java.io.*;
import java.net.*;
import java.util.*;

class MainClient  {

    ObjectOutputStream oos;
    ObjectInputStream ois;
    BufferedReader consoleIn;
    Socket sock;
    int id;
    Communicator comm;
    String xbeeFile;

    public MainClient(String serverAddr, int port, String xbeeFile) throws IOException {
		this.xbeeFile = xbeeFile;
		consoleIn = new BufferedReader(new InputStreamReader(System.in));
		sock = new Socket(serverAddr,port);
		id = ois.readInt();
		System.out.println("my is is "+id);
		// creation de l'objet permettant de communiquer avec le xbee
		comm = new Communicator(xbeeFile, sock);
		
		// creation du thread client
		ThreadClient t = new ThreadClient(comm);
		t.start();

    }

    public void mainLoop() {

		String line="";
		boolean stop = false;
		byte[] cmdCar = new byte[2];

		try {
		    while (!stop) {
				line = consoleIn.readLine();
				if (line.equals("AV")) {
				    // remplir cmdCar
				    // envoyer cmdCar au Xbee (cf. writeToXbee() dans Communicator)
				} else if (line.equals("TG")) {
			    // A COMPLETER
			    }
				// A COMPLETER avec les autres possibilités de saisie
				else if (line.equals("quit")) {
				    stop = true;
				}
		    }
		}
		catch(IOException e) {
		    System.out.println("cannot communicated with server. Aborting");
		}
    }
}
		
