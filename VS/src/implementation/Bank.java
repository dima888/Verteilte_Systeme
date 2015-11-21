package implementation;

import java.util.ArrayList;
import java.util.List;

import interfaces.GameComponent;

/**
 * Our Bank implementation
 * @author foxhound
 */
public class Bank implements GameComponent {

	private String gameID = "";
	
	List<Account> playerAccountList = new ArrayList<Account>();
	private int accountAmount = 1_000_000;
	
	/**
	 * FIXME: not defined in our UML
	 * Constructor
	 * Set a association with our game
	 * @param gameID - a game id
	 */
	public Bank(String gameID) {
		this.gameID = gameID;
	}
	
	public int getBankAmount() {
		return accountAmount;
	}
	
	/**
	 * Set new bank amount
	 * by successful setting of new amount, method return true, else false
	 * @param accountAmount
	 * @return boolean
	 */
	public boolean setBankAmount(int accountAmount) {
		try {
			this.accountAmount = accountAmount;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Method returns the id to related game
	 * @return String
	 */
	@Override
	public String getID() {
		return gameID;
	}
	
	/**
	 * Method add a player account to our bank
	 * by successful add from a player id return the method true, else false
	 * @param playerID - id from a player
	 * @return boolean  
	 */
	public boolean addAccount(String playerID) {		
		// precondition: for not dublication accounts
		for ( Account account : playerAccountList) {
			if ( account.getID().compareTo(playerID) == 0) {
				return false;
			}
		}		
		return playerAccountList.add(new Account(playerID));
	}
	
	/**
	 * Method delete a player account from our bank
	 * by successful delete return the method true, else false
	 * @param playerID - id from a player
	 * @return boolean
	 */
	public boolean removeAccount(String playerID) {
		for ( Account account : playerAccountList ) {
			if ( account.getID().compareTo(playerID) == 0 ) {
				return playerAccountList.remove(playerID);
			}
		}
		return false;
	}
	
	/**
	 * Method return a list with player accounts 
	 * @return List<String> / ArrayList
	 */
	public List<Account> getAccountList() {
		return playerAccountList;
	}
	
	/**
	 * Method return a acocunt from a player
	 * @param playerID - player id
	 * @return Account or null, if account not exist to param playerID
	 */
	public Account getAccountBy(String playerID) {
		for ( Account account : playerAccountList ) {
			if ( account.getID().compareTo(playerID) == 0) {
				return account;
			}
		}
		return null;
	}

}
