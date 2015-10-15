package rmi;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import config.DefaultConfiguration;
import server.ServerImpl;

public class RMIServer {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		
		// initialize a server object
		ServerImpl serverObject = new ServerImpl();
		
		// set registry from RMI 
		Registry registry = LocateRegistry.createRegistry(DefaultConfiguration.RMP_PORT);
		registry.bind(DefaultConfiguration.RMI_ID, serverObject);
		System.out.println("RMI is started");
	}
}
