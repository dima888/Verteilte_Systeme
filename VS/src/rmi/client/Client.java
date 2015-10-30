package rmi.client;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import config.DefaultConfiguration;
import rmi.interfaces.DiceRMI;


public class Client {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		DiceRMI obj = (DiceRMI)Naming.lookup(DefaultConfiguration.HOST + DefaultConfiguration.RMI_ID);
		System.out.println(obj.roll().getNumber());

	}
}
