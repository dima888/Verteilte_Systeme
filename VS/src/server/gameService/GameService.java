package server.gameService;

import static spark.Spark.*;

import com.google.gson.Gson;

import config.DefaultConfiguration;
import implementation.Game;
import implementation.Player;
import server.db.DataBase;

/**
 * Implements the resourcetype: Game of
 * 
 * @author Flah & Foxhound
 *
 */
public class GameService {
	
	public static void main(String[] args) {
		Gson gson = new Gson();				
		
		// Starts a new Game
		post("/games", (req, res) -> {
			res.type(DefaultConfiguration.RESPONSE_TYPE_JSON);
			res.status(201);
			Game newGame = new Game();
			
			// add game object in our db
			DataBase.write(DefaultConfiguration.DB_URL_WRITE, newGame);
			
			return gson.toJson(newGame);
		});
		
		// Adds a new player to a existing game
		put("/games/:gameid/players/:playerid", (req, res) -> {
			String gameID = req.params(":gameid");
			String playerID = req.params(":playerid");
			
			// get our game as gson
			String gameGson = DataBase.read(DefaultConfiguration.DB_URL_READ, gameID);
			
			// new code			
			if ( gameGson != null) {
				res.status(200);
				Player player = new Player(playerID);	
				
				// json to game object
				Game game = gson.fromJson(gameGson, Game.class);
				
				// precondition: player exist?
				if ( game.getPlayerByID(playerID) != null ) {
					return "Player exestiert bereits";
				}
				
				// add a player to our game object
				game.addPlayer(player);
				
				// save the object again in our db
				DataBase.write(DefaultConfiguration.DB_URL_WRITE, game);
				
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
			
			// get the game as gson from our db
			String gameGson = DataBase.read(DefaultConfiguration.DB_URL_READ, gameID);					
			
			// check if game exist
			if ( gameGson != null ) {
				
				// parse json to game object
				Game game = gson.fromJson(gameGson, Game.class);

				Player player = game.getPlayerByID(playerID);
				
				// check if the game include player with this id
				if ( player != null ) {															
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
							
			// get the game as gson from our db
			String gameGson = DataBase.read(DefaultConfiguration.DB_URL_READ, gameID);
			
			// Precondition
			if( gameGson != null ) {
				
				// parse json to game object
				Game game = gson.fromJson(gameGson, Game.class);
				
				// get player by id from our game object
				Player player = game.getPlayerByID(playerID);
				
				// precondition
				if ( player != null ) {
					
					// Toggled den Status
					player.setReady(! player.getReady()); 
					
					// save modify game object
					DataBase.write(DefaultConfiguration.DB_URL_WRITE, game);
					
					return player.getReady();
				} else {
					res.status(404);
					return "Spieler mit dieser ID existiert nicht!";
				}
			} else {
				res.status(404);
				return "Spiel existiert nicht!";
			}	
		});		
	}
}
