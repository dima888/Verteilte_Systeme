package interfaces;

import java.rmi.*;

/**
 * Definition from our Server Interface
 * @author foxhound
 *
 */
public interface ServerInterface extends Remote {

	/**
	 * Method add a with b
	 * @param a - value
	 * @param b - value
	 * @return int
	 * @throws RemoteException - dislodged/entfertes object is not available or connection lost
	 */
	public int add(int a, int b) throws RemoteException; 
	
}
