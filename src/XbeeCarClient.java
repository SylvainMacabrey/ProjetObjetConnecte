import java.io.IOException;

class XbeeCarClient  {

  public static void main(String []args) {

    MainClient client;

    if (args.length != 3) {
      System.out.println("usage: Client ip_server port xbee_file");
      System.exit(1);
    }

    try {
      // create main client
      String serverAddr = args[0];
      int port = Integer.parseInt(args[1]);
      client = new MainClient(serverAddr,port,args[2]);
      System.out.print(client);
      client.mainLoop();
    }
    catch(IOException e) {
      System.out.println("cannot connect to server : "+e.getMessage());
      System.exit(1);
    }
  }
}
		
