import java.io.*;
import java.net.Socket;

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
                switch (line) {
                    case "AV":
                        // remplir cmdCar
                        // envoyer cmdCar au Xbee (cf. writeToXbee() dans Communicator)
                        break;
                    case "RE":
                        // A COMPLETER
                        break;
                    case "TG":
                        // A COMPLETER
                        break;
                    case "TD":
                        // A COMPLETER
                        break;
                    case "RG":
                        // A COMPLETER
                        break;
                    case "RD":
                        // A COMPLETER
                        break;
                    case "ST":
                        // A COMPLETER
                        break;
                    case "GO":
                        // A COMPLETER
                        break;
                    case "SU":
                        // A COMPLETER
                        break;
                    case "SD":
                        // A COMPLETER
                        break;
                    case "GP":
                        // A COMPLETER
                        break;
                    case "quit":
                        stop = true;
                        break;
                }
		    }
		}
		catch(IOException e) {
		    System.out.println("cannot communicated with server. Aborting");
		}
    }
}
		
