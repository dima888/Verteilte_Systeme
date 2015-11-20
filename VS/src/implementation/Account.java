package implementation;

import interfaces.GameComponent;

/**
 * This class implemented a account to a bank
 * playerID have a association with a account
 * @author foxhound
 *
 */
public class Account implements GameComponent{

	private String playerID = "";
	private int saldo = 0;
	
	/**
	 * Constructor
	 * @param playerID - a player id
	 */
	public Account(String playerID) {
		this.playerID = playerID;
	}

	/**
	 * Method return the account id
	 * account id is equal to playerID
	 */
	@Override
	public String getID() {
		return playerID;
	}
	
	/**
	 * Method return the saldo to a account
	 * @return int
	 */
	public int getSaldo() {
		return saldo;
	}
	
}
