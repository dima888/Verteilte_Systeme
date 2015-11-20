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
