package rmi.interfaces;

import java.rmi.*;

import implementation.Roll;

/**
 * Definition from our Server Interface
 * @author foxhound
 *
 */

public interface DiceRMI extends Remote {
	
    Roll roll() throws RemoteException;
}
