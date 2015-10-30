package implementation;

import java.util.List;

/**
 * 
 * @author Flah
 *
 */
public class GameUtility {
	
	public static Boolean gameExists(String gameID, List<Game> gamesList) {
		for(Game g : gamesList) {
			if(g.getGameID().compareTo(gameID) == 0) {
				return true;
			}
		}	
		
		return false;
	}
	
	public static void gameAddPlayer(String gameID, Player p, List<Game> gamesList) {
		for(Game g : gamesList) {
			if(g.getGameID().compareTo(gameID) == 0) {
				g.addPlayer(p);
			}
		}	
	}
	
	public static Game getGame(String gameID, List<Game> gamesList) {
		for(Game g : gamesList) {
			if(g.getGameID().compareTo(gameID) == 0) {
				return g;
			}
		}	
		
		return null;
	}	
}
