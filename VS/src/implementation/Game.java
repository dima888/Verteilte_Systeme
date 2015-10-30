package implementation;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Game-Scheme
 * 
 * @author Flah
 * @see <a href="https://pub.informatik.haw-hamburg.de/home/pub/prof/kossakowski_klaus-peter/wise2015/verteiltesysteme/step2.raml">API</a>
 */
public class Game {
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
	
	/**
	 * Public constructor
	 * Assigns a unique identifier for a object
	 * Initializes the players collection
	 */
	public Game() {
		this.gameID = Integer.toString(gamesCounter);
		gamesCounter++;
		this.players = new ArrayList<>();
	}
	
	/**
	 * Adds a player to a game
	 * @param p - a player object
	 */
	public void addPlayer(Player p) {
		this.players.add(p);
	}
	
	/**
	 * Removes a player from a game
	 * @param p - a player object
	 */
	public void removePlayer(Player p) {
		this.players.remove(p);
	}
	
	/**
	 * @return List of all assgined players to this game
	 */
	public List<Player> getPlayersList() {
		return this.players;
	}
	
	/**
	 * @return Unique identifier for this game
	 */
	public String getGameID() {
		return this.gameID;
	}
}
