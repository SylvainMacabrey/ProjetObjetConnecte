import java.io.IOException;

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
				// lire une ligne dans le fichier xbee
				line = comm.readFromXbee();
				// analyzer la trame
				analyzer.analyzeFrame(line);
				// si position actuelle = celle du barycentre, arrêter la voiture
				Position posActuelle = new Position(analyzer.getLatitude(), analyzer.getLongitude(), analyzer.getAltitude());
				if(currentCenter == posActuelle) {
					byte[] cmdCar = new byte[2];
					cmdCar[0] = 10;
					cmdCar[1] = 2;
					comm.writeToXbee(cmdCar);
				}
				// si turn vaut 0, envoyer requete SETPOSITION et récupérer le barycentre courant
				turn  = (turn+1)%4;
				if(turn == 0) {
					currentCenter = comm.sendSetPositionRequest(analyzer.getLatitude(), analyzer.getLongitude(), analyzer.getAltitude());
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
		
