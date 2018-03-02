import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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
		byte[] cmdCar = new byte[16];

		try {
		    while (!stop) {
				line = consoleIn.readLine();
                switch (line) {
                    case "AV":
                        cmdCar[0] = 0;
                        cmdCar[1] = 1;
                        cmdCar[2] = 0;
                        cmdCar[3] = 1;
                        cmdCar[4] = 0;
                        cmdCar[5] = 0;
                        cmdCar[6] = 0;
                        cmdCar[7] = 0;

                        cmdCar[8] = 1;
                        cmdCar[9] = 0;
                        cmdCar[10] = 0;
                        cmdCar[11] = 0;
                        cmdCar[12] = 0;
                        cmdCar[13] = 0;
                        cmdCar[14] = 0;
                        cmdCar[15] = 0;

                        comm.writeToXbee(cmdCar);
                        break;
                    case "RE":
                        cmdCar[0] = 0;
                        cmdCar[1] = 1;
                        cmdCar[2] = 0;
                        cmdCar[3] = 1;
                        cmdCar[4] = 0;
                        cmdCar[5] = 0;
                        cmdCar[6] = 0;
                        cmdCar[7] = 0;

                        cmdCar[8] = 0;
                        cmdCar[9] = 1;
                        cmdCar[10] = 0;
                        cmdCar[11] = 0;
                        cmdCar[12] = 0;
                        cmdCar[13] = 0;
                        cmdCar[14] = 0;
                        cmdCar[15] = 0;
                        comm.writeToXbee(cmdCar);
                        break;
                    case "TG":
                        cmdCar[0] = 0;
                        cmdCar[1] = 1;
                        cmdCar[2] = 0;
                        cmdCar[3] = 1;
                        cmdCar[4] = 0;
                        cmdCar[5] = 0;
                        cmdCar[6] = 0;
                        cmdCar[7] = 0;

                        cmdCar[8] = 1;
                        cmdCar[9] = 1;
                        cmdCar[10] = 0;
                        cmdCar[11] = 0;
                        cmdCar[12] = 0;
                        cmdCar[13] = 0;
                        cmdCar[14] = 0;
                        cmdCar[15] = 0;
                        comm.writeToXbee(cmdCar);
                        break;
                    case "TD":
                        cmdCar[0] = 0;
                        cmdCar[1] = 1;
                        cmdCar[2] = 0;
                        cmdCar[3] = 1;
                        cmdCar[4] = 0;
                        cmdCar[5] = 0;
                        cmdCar[6] = 0;
                        cmdCar[7] = 0;

                        cmdCar[8] = 0;
                        cmdCar[9] = 0;
                        cmdCar[10] = 1;
                        cmdCar[11] = 0;
                        cmdCar[12] = 0;
                        cmdCar[13] = 0;
                        cmdCar[14] = 0;
                        cmdCar[15] = 0;
                        comm.writeToXbee(cmdCar);
                        break;
                    case "RG":
                        cmdCar[0] = 0;
                        cmdCar[1] = 1;
                        cmdCar[2] = 0;
                        cmdCar[3] = 1;
                        cmdCar[4] = 0;
                        cmdCar[5] = 0;
                        cmdCar[6] = 0;
                        cmdCar[7] = 0;

                        cmdCar[8] = 1;
                        cmdCar[9] = 0;
                        cmdCar[10] = 1;
                        cmdCar[11] = 0;
                        cmdCar[12] = 0;
                        cmdCar[13] = 0;
                        cmdCar[14] = 0;
                        cmdCar[15] = 0;
                        comm.writeToXbee(cmdCar);
                        break;
                    case "RD":
                        cmdCar[0] = 0;
                        cmdCar[1] = 1;
                        cmdCar[2] = 0;
                        cmdCar[3] = 1;
                        cmdCar[4] = 0;
                        cmdCar[5] = 0;
                        cmdCar[6] = 0;
                        cmdCar[7] = 0;

                        cmdCar[8] = 0;
                        cmdCar[9] = 1;
                        cmdCar[10] = 1;
                        cmdCar[11] = 0;
                        cmdCar[12] = 0;
                        cmdCar[13] = 0;
                        cmdCar[14] = 0;
                        cmdCar[15] = 0;
                        comm.writeToXbee(cmdCar);
                        break;
                    case "ST":
                        cmdCar[0] = 1;
                        cmdCar[1] = 1;
                        cmdCar[2] = 0;
                        cmdCar[3] = 1;
                        cmdCar[4] = 0;
                        cmdCar[5] = 0;
                        cmdCar[6] = 0;
                        cmdCar[7] = 0;

                        cmdCar[8] = 0;
                        cmdCar[9] = 0;
                        cmdCar[10] = 1;
                        cmdCar[11] = 0;
                        cmdCar[12] = 0;
                        cmdCar[13] = 0;
                        cmdCar[14] = 0;
                        cmdCar[15] = 0;
                        comm.writeToXbee(cmdCar);
                        break;
                    case "GO":
                        cmdCar[0] = 1;
                        cmdCar[1] = 1;
                        cmdCar[2] = 0;
                        cmdCar[3] = 1;
                        cmdCar[4] = 0;
                        cmdCar[5] = 0;
                        cmdCar[6] = 0;
                        cmdCar[7] = 0;

                        cmdCar[8] = 1;
                        cmdCar[9] = 0;
                        cmdCar[10] = 1;
                        cmdCar[11] = 0;
                        cmdCar[12] = 0;
                        cmdCar[13] = 0;
                        cmdCar[14] = 0;
                        cmdCar[15] = 0;
                        comm.writeToXbee(cmdCar);
                        break;
                    case "SU":
                        cmdCar[0] = 1;
                        cmdCar[1] = 1;
                        cmdCar[2] = 0;
                        cmdCar[3] = 1;
                        cmdCar[4] = 0;
                        cmdCar[5] = 0;
                        cmdCar[6] = 0;
                        cmdCar[7] = 0;

                        cmdCar[8] = 1;
                        cmdCar[9] = 0;
                        cmdCar[10] = 0;
                        cmdCar[11] = 0;
                        cmdCar[12] = 0;
                        cmdCar[13] = 0;
                        cmdCar[14] = 0;
                        cmdCar[15] = 0;
                        comm.writeToXbee(cmdCar);
                        break;
                    case "SD":
                        cmdCar[0] = 1;
                        cmdCar[1] = 1;
                        cmdCar[2] = 0;
                        cmdCar[3] = 1;
                        cmdCar[4] = 0;
                        cmdCar[5] = 0;
                        cmdCar[6] = 0;
                        cmdCar[7] = 0;

                        cmdCar[8] = 0;
                        cmdCar[9] = 1;
                        cmdCar[10] = 0;
                        cmdCar[11] = 0;
                        cmdCar[12] = 0;
                        cmdCar[13] = 0;
                        cmdCar[14] = 0;
                        cmdCar[15] = 0;
                        comm.writeToXbee(cmdCar);
                        break;
                    case "GP":
                        ArrayList<Position> pos = comm.sendGetPositionRequest();
                        for (Position p: pos) {
                            System.out.println(p);
                        }
                        break;
                    case "quit":
                        stop = true;
                        break;
                }
		    }
		}
		catch(IOException e) {
		    System.out.println("cannot communicated with server. Aborting");
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
		
