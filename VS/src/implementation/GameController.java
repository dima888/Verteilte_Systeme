package implementation;

import static spark.Spark.post;
import static spark.SparkBase.port;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mashape.unirest.request.HttpRequest;

import config.DefaultConfiguration;
import interfaces.GameComponent;

/**
 * This class manage all components
 * @author foxhound
 *
 */
public class GameController {

	private static List<GameComponent> gameList = new ArrayList<GameComponent>();
	
	/**
	 * Method checked, if the current game id exist
	 * @param gameID - a game id
	 * @return boolean
	 */
	public static boolean gameExist(String gameID) {
		return existExecuter(gameID, gameList);
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
	 * Method add a game in our db by post
	 * By sucessful add, return the method true, else false
	 * @param game - a game object
	 * @return boolean
	 */
	public static boolean addGame(Game game) {		
		return gameList.add(game);
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
	

}
