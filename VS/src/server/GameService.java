package server;

import static implementation.GameUtility.gameAddPlayer;
import static implementation.GameUtility.gameExists;
import static implementation.GameUtility.getGame;
import static implementation.PlayerUtility.getPlayer;
import static implementation.PlayerUtility.playerExists;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

import config.DefaultConfiguration;
import implementation.Game;
import implementation.Player;
import implementation.Roll;

/**
 * Implements the resourcetype: Game of
 * 
 * @author Flah
 *
 */
public class GameService {
	public static void main(String[] args) {
		Gson gson = new Gson();
		Random random = new Random();
		List<Game> gamesList = new ArrayList<>();
		
		// Gives a single dice roll
		get("/dice", (req, res) -> {
			res.type(DefaultConfiguration.RESPONSE_TYPE_JSON);
			res.status(200);
			return gson.toJson(new Roll(random.nextInt(6) + 1));
		});
		
		// Starts a new Game
		post("/games", (req, res) -> {
			res.type(DefaultConfiguration.RESPONSE_TYPE_JSON);
			res.status(201);
			Game newGame = new Game();
			gamesList.add(newGame);
			return gson.toJson(newGame);
		});
		
		// Adds a new player to a existing game
		put("/games/:gameid/players/:playerid", (req, res) -> {
			String gameID = req.params(":gameid");
			String playerID = req.params(":playerid");
			
			if(gameExists(gameID, gamesList)) {
				res.status(200);
				Player p = new Player(playerID);
				gameAddPlayer(gameID, p, gamesList);
				return gson.toJson(p);
			} else {
				res.status(404);
				return "Spiel existiert nicht!";				
			}
		});
		
		// Retrives if the player is ready
		get("/games/:gameid/players/:playerid/ready", (req, res) -> {
			String gameID = req.params(":gameid");
			String playerID = req.params(":playerid");		
			
			if(gameExists(gameID, gamesList)) {
				Game g = getGame(gameID, gamesList);
				List<Player> playersList = g.getPlayersList();
				
				if(playerExists(playerID, playersList)) {
					Player p = getPlayer(playerID, playersList);
					res.status(200);
					return p.getReady();
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
			
			if(gameExists(gameID, gamesList)) {
				Game g = getGame(gameID, gamesList);
				List<Player> playersList = g.getPlayersList();
				
				if(playerExists(playerID, playersList)) {
					Player p = getPlayer(playerID, playersList);
					p.setReady(! p.getReady()); // Toggled den Status
					return p.getReady();
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
