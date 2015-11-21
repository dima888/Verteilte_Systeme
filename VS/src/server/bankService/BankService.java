package server.bankService;

import static spark.Spark.post;

import static spark.Spark.get;

import com.google.gson.Gson;

import config.DefaultConfiguration;
import implementation.Account;
import implementation.Bank;
import implementation.Game;
import implementation.Player;
import server.db.DataBase;

import static spark.Spark.*;

/**
 * Our Bank service
 * @author foxhound
 */
public class BankService {
	
	/**
	 * Service starter
	 * @param args
	 */
	public static void main(String[] args) {
		
		// gson object 
		Gson gson = new Gson();
		
		// delete this, is only for testing
		port(4568);
		
//========================================================================
		/**
		 * Service create a account
		 * ein Konto erstellt werden kann mit
		 * post /banks/{gameid}/players
		 */
		post("/banks/:gameID/players", (req, res) -> {
						
			// get game id from client input
			String gameID = req.params("gameID");
			
			// get our game as gson
			String gameGson = DataBase.read(DefaultConfiguration.DB_URL_READ, gameID);
			
			// Precondition
			if ( gameGson == null ) {
				res.status(404);
				return "Spiel existiert nicht!";
			}
			
			// parse json to game object
			Game game = gson.fromJson(gameGson, Game.class);					
			
			// get bank from our game
			Bank bank = game.getBank();					
			
			// create accounts for the bank in all clients
			for ( Player player : game.getPlayersList() ) {
				bank.addAccount(player.getID());
			}
			
			// save modify game object
			DataBase.write(DefaultConfiguration.DB_URL_WRITE, game);
			
			// return result
			res.type(DefaultConfiguration.RESPONSE_TYPE_JSON);
			res.status(201);
			return gson.toJson(bank);
		});
//========================================================================
		
//========================================================================		
		/**
		 * call account balance / kontostand abfragen
		 * der Kontostand abgefragt werden kann mit
		 * get /banks/{gameid}/players/{playerid}
		 */
		get("/banks/:gameID/players/:playerID", (req, res) -> {		
			
			// get game id from client input
			String gameID = req.params("gameID");		
			
			// get player id from client input
			String playerID = req.params("playerID");
			
			// get the game as gson from our db
			String gameGson = DataBase.read(DefaultConfiguration.DB_URL_READ, gameID);
			
			// precondition
			if ( gameGson == null ) {
				res.status(404);
				return "Spiel existiert nicht!";
			}
											
			// parse json to game object
			Game game = gson.fromJson(gameGson, Game.class);
						
			// precondition
			if ( game.getPlayerByID(playerID) == null ) {
				res.status(404);
				return "Spieler mit dieser ID existiert nicht!";
			}
			
			// get our bank to the game id
			Bank bank = game.getBank();
			
			// get our account to the param player
			Account account = bank.getAccountBy(playerID);
			
			// get the account balance of this bank to a player id
			int playerMount = account.getSaldo();
			
			// define 
			res.status(200);		
			
			// return result
			return gson.toJson(playerMount);			
		}) ;
//========================================================================		
	
//========================================================================
		/**
		 * Geld von der Bank überwiesen werden kann mit
		 * post /banks/{gameid}/transfer/to/{to}/{amount}
		 */
		post("/banks/:gameID//transfer/to/:to/:amount", (req, res) -> {
			
			// get user input value
			String gameID = req.params("gameID");
			String playerID = req.params("to");
			int amount = Integer.parseInt(req.params("amount")); 
			
			// get game as gson from our db
			String gameGson = DataBase.read(DefaultConfiguration.DB_URL_READ, gameID);
			
			// precondtion: checked if the game exist
			if (gameGson == null) {
				res.status(404);
				return "Spiel existiert nicht!";
			}
									
			// gson to game object
			Game game = gson.fromJson(gameGson, Game.class);
			
			// precondtion: check if the player exist
			if (game.getPlayerByID(playerID) == null) {
				res.status(404);
				return "Spieler mit dieser ID existiert nicht!";
			}
			
			// get the bank to our game
			Bank bank = game.getBank();
			
			// transfer money from bank to a player
			boolean transferSuccess = game.transfer(bank, bank.getAccountBy(playerID), amount, "what ever?");
			
			if (transferSuccess) {
				// save the modify game object in our db
				DataBase.write(DefaultConfiguration.DB_URL_WRITE, game);
				
				// return result
				res.status(200);
				return game.getTransaction();
			} else {
				// return failed result
				res.status(400);
				return "transaction was not successful";
			}
		});
//========================================================================		
		
		
		
		
		
	}
}












