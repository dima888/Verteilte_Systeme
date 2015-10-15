package server;

import java.rmi.*;
import java.rmi.server.*;
import interfaces.ServerInterface;

/**
 * This class capsulate the server implementation
 * @author foxhound
 *
 */
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 * @throws RemoteException
	 */
	public ServerImpl() throws RemoteException {
		super();
	}

	/**
	 * Method add two variables
	 */
	@Override
	public int add(int a, int b) throws RemoteException {
		return a + b;
	}


}
