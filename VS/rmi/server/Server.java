package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import config.DefaultConfiguration;
import implementation.DiceRMIImpl;

/**
 * Our RMI Server starter
 * @author Flah
 *
 */
public class Server {
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		Naming.rebind(DefaultConfiguration.RMI_ID, new DiceRMIImpl());
		System.out.println("Server started at Port: " + Registry.REGISTRY_PORT);
	}
}