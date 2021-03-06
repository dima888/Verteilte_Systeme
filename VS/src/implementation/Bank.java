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
	
	// our transaction class
	Transaction transaction = new Transaction();
	
	/**
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
	 * Method return the last transaction object
	 * @return Transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	
	/**
	 * Method tranfer a amount from the bank, to our player account
	 * @param bank - a bank object from a game
	 * @param account - a player account
	 * @param amount - transfer amount
	 * @param reason - what ever
	 * @return boolean
	 */
	public boolean transferPull(String playerID, int amount, String reason) {
		return transaction.transferPull(this, getAccountBy(playerID), amount, reason);
	}
	
	/**
	 * Method push money from the bank to a player
	 * @param playerID -  id from a player 
	 * @param amount - tranfer amount
	 * @param reason - what ever
	 * @return boolean
	 */
	public boolean transferPush(String playerID, int amount, String reason) {
		return transaction.transferPush(this, getAccountBy(playerID), amount, reason);
	}
	
	/**
	 * Method transfer a mount from a account to a other account
	 * @param accountFrom - sub money
	 * @param accountTo - add money
	 * @param amount - +- money value
	 * @param reason - why you do why you do a transaction?
	 * @return boolean 
	 */
	public boolean transfer(String playerIDFrom, String playerIDTo, int amount, String reason) {
		return transaction.transfer(getAccountBy(playerIDFrom), getAccountBy(playerIDTo), amount, reason);
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
