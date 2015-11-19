package implementation;

import java.util.ArrayList;
import java.util.List;

import interfaces.GameComponent;

/**
 * This class manage all components
 * @author foxhound
 *
 */
public class GameController {

	private static List<GameComponent> gameList = new ArrayList<GameComponent>();
	private static List<GameComponent> bankList = new ArrayList<GameComponent>();
	private static List<GameComponent> playerList = new ArrayList<GameComponent>();
	
	/**
	 * Method checked, if the current game id exist
	 * @param gameID - a game id
	 * @return boolean
	 */
	public static boolean gameExist(String gameID) {
		return existExecuter(gameID, gameList);
	}
	
	/**
	 * Method checked, if current bank id exist
	 * @param bankID - a bank id
	 * @return
	 */
	public static boolean bankExist(String bankID) {
		return existExecuter(bankID, bankList);
	}
	
	/**
	 * Method checked, if a player exist
	 * @param playerID - a player id
	 */
	public static boolean playerExist(String playerID) {
		return existExecuter(playerID, playerList);
	}
	
	/**
	 * Method checked, if a player exist in the param list
	 * @param playerID -  player id
	 * @param playerList - a player list
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public static<T> boolean playerExist(String playerID, List<T> playerList) {
		boolean result = false;
		try {
			result = existExecuter(playerID, (List<GameComponent>) playerList);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("playerExist is not a GameComponent du Spinner!");
		}
		return result;
	}
	
	/**
	 * Method add a game in our gameList
	 * By sucessful add, return the method true, else false
	 * @param game - a game object
	 * @return boolean
	 */
	public static boolean addGame(Game game) {
		return gameList.add(game);
	}
	
	/**
	 * Method add a bank in our gameList
	 * By sucessful add, return the method true, else false
	 * @param bank - a bank object
	 * @return boolean
	 */
	public static boolean addBank(Bank bank) {
		return bankList.add(bank);
	}
	
	/**
	 * Method add a player in our playerList
	 * By sucessful add, return the method true, else false
	 * @param player - a player Object
	 * @return boolean
	 */
	public static boolean addPlayer(String gameID, Player player) {
		return addplayerExecuter(gameID, player);
	}
	
	/**
	 * Method return by success a game object to a game id, else null
	 * @param gameID - a game id
	 * @return Game
	 */
	public static Game getGame(String gameID) {
		for(Object game : gameList) {
			if (((GameComponent) game).getID().compareTo(gameID) == 0) {
				return (Game) game;
			}
		}			
		return null;
	}	
	
	/**
	 * Method return a player 
	 * @param gameID - game id
	 * @param playerID - player id
	 * @return Player or null
	 */
	public static Player getPlayer(String gameID, String playerID) {
		Game game = getGame(gameID);
		return game.getPlayerByID(playerID);
	}
	
//====================================================================
// 						PRIVATE HELPER METHOD'S	
//====================================================================	
	/**
	 * Helper method for gameExist / BankExist / Account Exist
	 * @param id
	 * @param objectList
	 * @return
	 */
	private static boolean existExecuter(String id, List<GameComponent> objectList) {
		for ( GameComponent currentObjectElement : objectList ) {
			if ( currentObjectElement.getID().compareTo(id) == 0 ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method add a player to a game by game id
	 * @param id - a game id
	 * @param player - a player object
	 * @return boolean
	 */
	private static boolean addplayerExecuter(String id, Player player) {
		for ( Object game : gameList ) {
			return ((Game) game).addPlayer(player); 
		}
		return false;
	}
}
