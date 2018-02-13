import java.io.*;
import java.net.*;
import java.util.*;

class ThreadClient extends Thread  {

    Communicator comm;
    GPSAnalyzer analyzer;
    Position currentCenter; // position courantedu barycentre

    public ThreadClient(Communicator comm) {
		this.comm = comm;
		analyzer = new GPSAnalyzer();
    }

    public void run() {

		String line="";
		boolean stop = false;
		int turn = 0;
		try {
		    while (!stop) {
			/* A COMPLETER :
			   - lire une ligne dans le fichier xbee
			   - analyzer la trame
			   - si position actuelle = celle du barycentre, arrêter la voiture
			   - si turn vaut 0, envoyer requete SETPOSITION et récupérer le barycentre courant		  
			*/
			turn  = (turn+1)%4;
		    }
		}
		catch(IOException e) {
		    System.out.println("cannot communicated with server. Aborting");
		}
    }
}
		
