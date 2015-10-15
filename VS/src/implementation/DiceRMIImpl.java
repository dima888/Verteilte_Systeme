package implementation;

import java.rmi.*;

import implementation.Roll;
import interfaces.DiceRMI;

/**
 * Implementation of DiceRMI Interface
 * @author foxhound
 *
 */

public class DiceRMIImpl implements DiceRMI {
	
	@Override
    public Roll roll() throws RemoteException {
    	return new Roll(5);
    }
}
