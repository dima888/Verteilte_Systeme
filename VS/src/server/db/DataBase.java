package server.db;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.SparkBase.port;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import config.DefaultConfiguration;
import implementation.Game;

/**
 * This class saved our game objects
 * @author foxhound
 *
 */
public class DataBase {
	
	public List<Game> gameList = new ArrayList<Game>();
	
	
	/**
	 * save game
	 * @param game
	 * @return
	 */
	public boolean save(Game game) {
		
		for (Game currentGame : gameList) {
			if (currentGame.getID().compareTo(game.getID()) == 0) {
				gameList.remove(currentGame);
				return gameList.add(game);				
			}
		}		
		return gameList.add(game);
	}
	
	/**
	 * Method get you a game by id
	 * @param id
	 * @return
	 */
	public Game getGame(String id) {
		for ( Game game : gameList ) {
			if ( game.getID().compareTo(id) == 0 ) {
				return game;
			}
		}
		return null;
	}
	
	/**
	 * delete game
	 * @param game
	 * @return
	 */
	public boolean delete(Game game) {
		return gameList.remove(game);
	}
	
	
	/**
	 * Method send a object in json to our db
	 * @param url - url db
	 * @param game - game object
	 * Example: save("http://localhost:7777/db/save/", new Game())  
	 */
	synchronized public static void write(String url, Game game) {		
		Gson gson = new Gson();
		String modifyUrl = url + gson.toJson(game);		
		HttpRequest request = new HttpRequest(com.mashape.unirest.http.HttpMethod.POST, modifyUrl);
		com.mashape.unirest.http.HttpClientHelper.requestAsync(request, null, null);
	}
	
	/**
	 * Method read from our database
	 * @param url - link to our db
	 * @param gameID - game id
	 * @return
	 */
	synchronized public static String read(String url, String gameID) {
		HttpRequest request = new HttpRequest(com.mashape.unirest.http.HttpMethod.GET, url + gameID);		
		HttpResponse<String> response = null;
		
		try {
			response = com.mashape.unirest.http.HttpClientHelper.request(request, String.class);
		} catch (Exception e) {
			return null;
		}			
		
		// precondition
		if ( response.getBody().compareTo("null") == 0 ) {
			return null;
		}
		
		return response.getBody();
	}

	/**
	 * Our DB-Service
	 * @param args
	 */
	public static void main(String[] args) {

		// create db object
		DataBase dataBase = new DataBase();		
		
		port(7777);		
		Gson gson = new Gson();
				
		// db write service
		post("/db/write/:game", (req, res) -> {
					
			String gameAsJson= req.params("game");
			Game game = gson.fromJson(gameAsJson, Game.class);
			
			dataBase.save(game);
			System.out.println(dataBase.gameList);
					
			res.type(DefaultConfiguration.RESPONSE_TYPE_JSON);
			res.status(201);
			return "save object";
		});
		
		// db read service
		get("/db/read/:gameID", (req, res) -> {			
			String gameID= req.params("gameID");			
			Game game = dataBase.getGame(gameID);
			
			System.out.println(dataBase.gameList);
			
			if ( game == null ) {
				res.status(404);
			} else {
				res.status(200);
			}
						
			res.type(DefaultConfiguration.RESPONSE_TYPE_JSON);			
			return gson.toJson(game);
		});
		
	}
}
