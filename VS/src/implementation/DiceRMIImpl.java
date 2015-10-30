package implementation;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import rmi.interfaces.DiceRMI;



/**
 * Implementation of DiceRMI Interface
 * @author foxhound
 *
 */
public class DiceRMIImpl extends UnicastRemoteObject implements DiceRMI {

	private static final long serialVersionUID = 1L;
	
	public DiceRMIImpl() throws RemoteException {
		super();
	}

	@Override
	public Roll roll() throws RemoteException {
		return new Roll(new Random().nextInt(6) + 1);
	}

}