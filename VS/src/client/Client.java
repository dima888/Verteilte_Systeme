package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import config.DefaultConfiguration;
import interfaces.ServerInterface;

public class Client {

	
	
	
	/**
	 * Regestry our client
	 * @param args
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(DefaultConfiguration.HOST, DefaultConfiguration.RMP_PORT);
		ServerInterface remote = (ServerInterface) registry.lookup(DefaultConfiguration.RMI_ID);
		
		int remoteResult = remote.add(11, 12);
		System.out.println("Remote Result: " + remoteResult);
	}
}
