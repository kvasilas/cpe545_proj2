import java.rmi.*;
import java.util.*;

public interface QueryInterface extends Remote {
    public String getTeamForPlayer(String name) throws RemoteException;
}