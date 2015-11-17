package client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RestoplyClient {
	public static void main(String[] args) throws UnirestException {
		String server = "http://localhost:4567";
		HttpResponse request = null;
		
		// Neues Spiel erzeugen
		request = Unirest.post(server + "/games").asJson();
		System.out.println("Neues Spiel erzeugen:\n"
						 + request.getBody().toString()
						 + "\n");
		
		// Neuen Spieler für Spiel anlegen
		request = Unirest.put(server + "/games/0/players/flah")
				  .header("accept", "application/json")
				  .asJson();
		System.out.println("Spieler zu einem Spiel hinzufügen:\n" 
				  		 + request.getBody().toString()
				  		 + "\n");		
		
		// Spielerstatus abfragen
		request = Unirest.get(server + "/games/0/players/flah/ready")
				  .asString();
		System.out.println("Spielerstatus abfragen:\n"
				  		 + request.getBody().toString()
				  		 + "\n");	
		
		// Spielerstatus aendern
		request = Unirest.put(server + "/games/0/players/flah/ready")
				  .asString();
		System.out.println("Spielerstatus aendern:\n"
				  		 + request.getBody().toString()
				  		 + "\n");			
	}
}
