import java.util.*;
import java.rmi.*;
import java.net.*;
import java.rmi.registry.*;

public class RoughClient {
    public static void main(String arg[]){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the server address:");
        String address = scan.nextLine();
        System.out.println("Enter the port number:");
        int port = scan.nextInt();
        QueryInterface server;
        Registry registry;

        int run = 1;
        System.out.println("Running on address: "+ address + ": "+ port);
        System.out.println("Press 0 to stop program");
        while(run==1){
            System.out.println("Enter player name:");
            String player = scan.nextLine();
            if(player.equals("0")){
                run = 0;
            }
            else{
                System.out.println("Name: "+ player);
                try{
                    registry = LocateRegistry.getRegistry(address, port);
                    server = (QueryInterface)(registry.lookup("Server"));
                    String team = server.getTeamForPlayer(player);
                    System.out.println("Team: "+team+"\n");
                }
                catch(RemoteException error){
                    error.printStackTrace();
                }
                catch(NotBoundException error){
                    System.err.print(error);
                }
            }
        }
    }
}