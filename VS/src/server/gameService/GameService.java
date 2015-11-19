package server.gameService;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.List;

import com.google.gson.Gson;

import config.DefaultConfiguration;
import implementation.Game;
import implementation.GameController;
import implementation.Player;

/**
 * Implements the resourcetype: Game of
 * 
 * @author Flah
 *
 */
public class GameService {
	public static void main(String[] args) {
		Gson gson = new Gson();
//		List<Game> gamesList = new ArrayList<>();
		
		// Starts a new Game
		post("/games", (req, res) -> {
			res.type(DefaultConfiguration.RESPONSE_TYPE_JSON);
			res.status(201);
			Game newGame = new Game();
			
			// old colde
			//gamesList.add(newGame);
			
			// new code
			GameController.addGame(newGame);
			return gson.toJson(newGame);
		});
		
		// Adds a new player to a existing game
		put("/games/:gameid/players/:playerid", (req, res) -> {
			String gameID = req.params(":gameid");
			String playerID = req.params(":playerid");
			
			// new code
			if ( GameController.gameExist(gameID) ) {
				res.status(200);
				Player player = new Player(playerID);				
				GameController.addPlayer(gameID, player);
				return gson.toJson(player);
			} else {
				res.status(404);
				return "Spiel existiert nicht!";
			}
		});
		
		// Retrives if the player is ready
		get("/games/:gameid/players/:playerid/ready", (req, res) -> {
			String gameID = req.params(":gameid");
			String playerID = req.params(":playerid");		
			
			// new code
			if ( GameController.gameExist(gameID) ) {
				Game game = GameController.getGame(gameID);
				
				List<Player> playersList = game.getPlayersList(); 
				
				if ( GameController.playerExist(gameID, playersList) ) {
					Player player = GameController.getPlayer(gameID, playerID);
					res.status(200);
					return player.getReady();
				} else {
					res.status(404);
					return "Spieler existiert nicht!";
				}
			} else {
				res.status(404);
				return "Spiel existiert nicht!";
			}
		});		
		
		// Changes the readystate of a player
		put("/games/:gameid/players/:playerid/ready", (req, res) -> {
			String gameID = req.params(":gameid");
			String playerID = req.params(":playerid");		
			
			// new code
			if( GameController.gameExist(gameID) ) {
				Game game = GameController.getGame(gameID);
				List<Player> playersList = game.getPlayersList();
				
				if( GameController.playerExist(playerID, playersList) ) {
					Player player = GameController.getPlayer(gameID, playerID);
					player.setReady(! player.getReady()); // Toggled den Status
					return player.getReady();
				} else {
					res.status(404);
					return "Spieler existiert nicht!";
				}
			} else {
				res.status(404);
				return "Spiel existiert nicht!";
			}	
		});		
	}
}
