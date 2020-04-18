import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.util.*;
//import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements QueryInterface{
    private ArrayList<String> players = new ArrayList<>(Arrays.asList("Mark Ingram-Ravens",
                                                                        "Devin Singletary-Bills",
                                                                        "Joe Mixon-Bengals",
                                                                        "Nick Chubb-Browns",
                                                                        "Phillip Lindsay-Broncos",
                                                                        "David Johnson-Texans",
                                                                        "Marlon Mack-Colts",
                                                                        "Leonard Fournette-Jaguars",
                                                                        "Damien Williams-Chiefs",
                                                                        "Melvin Gordon-Chargers",
                                                                        "Josh Jacobs-Raiders",
                                                                        "Jordan Howard-Dolphins",
                                                                        "Sony Michel-Patriots",
                                                                        "Le'Veon Bell-Jets",
                                                                        "James Conner-Steelers",
                                                                        "Derrick Henry-Titans",
                                                                        "Kenyan Drake-Cardinals",
                                                                        "Todd Gurley-Falcons",
                                                                        "Christian McCaffrey-Panthers",
                                                                        "David Montgomery-Bears",
                                                                        "Ezekiel Elliott-Cowboys",
                                                                        "Kerryon Johnson-Lions",
                                                                        "Aaron Jones-Packers",
                                                                        "Malcom Brown-Rams",
                                                                        "Dalvin Cook-Vikings",
                                                                        "Alvin Kamara-Saints",
                                                                        "Saquon Barkley-Giants",
                                                                        "Miles Sanders-Eagles",
                                                                        "Chris Carson-Seahawks",
                                                                        "Raheem Mostert-49ers",
                                                                        "Ronald Jones-Buccaneers",
                                                                        "Derrius Guice-Redskins"));
    private String addr;
    private int port;
    private Registry reg;
    
    public Server() throws RemoteException{
        /*
        establish remote connection
        */
        try{
            addr = InetAddress.getLocalHost().toString();
        } 
        catch(Exception e){
            System.out.println("Unable to get Inet Address");
        }
        port = 1099;
        System.out.println("Server connection established on: "+addr+":"+port);
        /* 
        Create Registry on Server port 1099
        */
        try{
            reg = LocateRegistry.createRegistry(port);
            reg.rebind("Server", this);
        } catch(RemoteException e){
            System.out.println(e);
        }
    }

    public String getTeamForPlayer(String name) throws RemoteException{
        ArrayList<String> teams = new ArrayList<String>();
        for(int i=0; i<players.size(); i++){
            String iFirstname = players.get(i).split("-")[0];
            if(name.equals(iFirstname))
            teams.add(players.get(i).split("-")[1]);
        }
        if(teams.size()==0)
            teams.add("Player Not Found: Check spelling");
        String output = "";
        for(int i = 0; i<teams.size(); i++){
            output += teams.get(i)+", ";
        }
        return output;
    }
    public static void main(String[] args){
        try{
            Server server = new Server();
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}