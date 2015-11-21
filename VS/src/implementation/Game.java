package implementation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.GameComponent;

/**
 * Implementation of the Game-Scheme
 * 
 * @author Flah
 * @see <a href="https://pub.informatik.haw-hamburg.de/home/pub/prof/kossakowski_klaus-peter/wise2015/verteiltesysteme/step2.raml">API</a>
 */
public class Game implements GameComponent {
	/**
	 * Object-Counter used as unique identifier 
	 */
	private static int gamesCounter = 0;
	
	/** 
	 * Unique identifier of a Game-Object
	 */
	private String gameID;
	
	/**
	 * Collection of all assigned players
	 */
	private List<Player> players;
	
	// the bank of our game
	private Bank bank;
	
	// our transaction class
	Transaction transaction = new Transaction();
	
	/**
	 * Public constructor
	 * Assigns a unique identifier for a object
	 * Initializes the players collection
	 */
	public Game() {
		this.gameID = Integer.toString(gamesCounter);
		bank = new Bank(gameID);
		gamesCounter++;
		this.players = new ArrayList<>();
	}
	
	/**
	 * Method tranfer a amount from the bank, to our player account
	 * @param bank - a bank object from a game
	 * @param account - a player account
	 * @param amount - transfer amount
	 * @return boolean
	 */
	public boolean transfer(Bank bank, Account account, int amount, String reason) {
		return transaction.transfer(bank, account, amount, reason);
	}
	
	/**
	 * Method transfer a mount from a account to a other account
	 * @param accountFrom - sub money
	 * @param accountTo - add money
	 * @param amount - +- money value
	 * @param reason - why you do why you do a transaction?
	 * @return boolean 
	 */
	public boolean transfer(Account accountFrom, Account accountTo, int amount, String reason) {
		return transfer(accountFrom, accountTo,amount, reason);
	}
	
	/**
	 * Method return the last transaction object
	 * @return Transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	
	/**
	 * Method return our bank object to current game
	 * @return Bank	
	 */
	public Bank getBank() {
		return bank;
	}
	
	/**
	 * Adds a player to a game
	 * @param p - a player object
	 */
	public boolean addPlayer(Player p) {
		return this.players.add(p);
	}
	
	/**
	 * Removes a player from a game
	 * @param p - a player object
	 */
	public boolean removePlayer(Player p) {
		return this.players.remove(p);
	}
	
	/**
	 * @return List of all assgined players to this game
	 */
	public List<Player> getPlayersList() {
		return this.players;
	}
	
	/**
	 * Method return a player by id
	 * By success return the method a player, else null
	 * @param id - player id
	 * @return Player
	 */
	public Player getPlayerByID(String id) {
		for ( Player player : players ) {
			if ( player.getID().compareTo(id) == 0 ) {
				return player;
			}
		}
		return null;
	}
	
	/**
	 * @return Unique identifier for this game
	 */
	@Override
	public String getID() {
		return this.gameID;
	}
}
