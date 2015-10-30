package implementation;

import java.util.List;

public class PlayerUtility {
	
	public static Boolean playerExists(String playerID, List<Player> playersList) {
		for(Player p : playersList) {
			if(p.getPlayerID().compareTo(playerID) == 0) {
				return true;
			}
		}	
		
		return false;
	}
	
	public static Player getPlayer(String playerID, List<Player> playersList) {
		for(Player p : playersList) {
			if(p.getPlayerID().compareTo(playerID) == 0) {
				return p;
			}
		}	
		
		return null;
	}		
}
