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
					byte[] cmdCar = new byte[16];
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
		
