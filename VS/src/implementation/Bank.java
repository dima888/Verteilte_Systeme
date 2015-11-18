package implementation;

import java.util.ArrayList;
import java.util.List;

/**
 * Our Bank implementation
 * @author foxhound
 */
public class Bank {

	private String gameID = "";
	List<String> playerIDList = new ArrayList<String>();
	
	/**
	 * FIXME: not defined in our UML
	 * Constructor
	 * Set a association with our game
	 * @param gameID - a game id
	 */
	public Bank(String gameID) {
		this.gameID = gameID;
	}
	
	/**
	 * Method returns the id to related game
	 * @return String
	 */
	public String getGameID() {
		return gameID;
	}
	
	/**
	 * Method add a player account to our bank
	 * by successful add from a player id return the method true, else false
	 * @param playerID - id from a player
	 * @return boolean  
	 */
	public boolean addAccount(String playerID) {
		// precondition: player id exist once in the list
		if ( playerIDList.contains(playerID) ) {
			return false;
		}	
		return playerIDList.add(playerID);
	}
	
	/**
	 * Method delete a player account from our bank
	 * by successful delete return the method true, else false
	 * @param playerID - id from a player
	 * @return boolean
	 */
	public boolean removeAccount(String playerID) {
		return playerIDList.remove(playerID);
	}
	
	/**
	 * Method return a list with player accounts 
	 * @return List<String> / ArrayList
	 */
	public List<String> getAccounts() {
		return playerIDList;
	}
}
