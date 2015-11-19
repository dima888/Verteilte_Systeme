package implementation;

import interfaces.GameComponent;

/**
 * This class implemented a account to a bank
 * @author foxhound
 *
 */
public class Account implements GameComponent{

	private int playerID = 0;
	private int saldo = 0;
	
	/**
	 * Constructor
	 * @param playerID - a player id
	 */
	public Account(int playerID) {
		this.playerID = playerID;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
