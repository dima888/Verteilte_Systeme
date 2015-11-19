package server.bankService;

import static spark.Spark.post;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;

import com.google.gson.Gson;

import config.DefaultConfiguration;
import implementation.Bank;
import implementation.GameController;

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
		
//========================================================================
		/**
		 * Service create a account
		 * ein Konto erstellt werden kann mit
		 * post /banks/{gameid}/players
		 */
		post("/banks/:gameID/players", (req, res) -> {
			
			// get game id from client input
			String gameID = req.params("gameID");
			
			// create a new bank with client game id
			Bank bank = new Bank(gameID);					
			
			// save the current bank in our banklist object
			GameController.addBank(bank);
			
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
			
			// search the right bank
			
			// get the account balance of this bank to a player id
			
			// return result
			res.status(200);
			return "";
		}) ;
//========================================================================		
		
	}
}












