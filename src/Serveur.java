import java.io.IOException;

/**
 * Created by sylva on 26/02/2018.
 */
public class Serveur {

    public static void main(String []args) {

        MainServer server = null;

        if (args.length != 1) {
            System.out.println("usage: PositionServer port");
            System.exit(1);
        }

        try {
            int port = Integer.parseInt(args[0]);
            server = new MainServer(port);
        }
        catch(IOException e) {
            System.out.println("cannot create the server : "+e.getMessage());
            System.exit(1);
        }
        server.mainLoop();
    }

}
