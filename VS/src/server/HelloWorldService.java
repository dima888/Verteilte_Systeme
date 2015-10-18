package server;

import static spark.Spark.*;
import static spark.SparkBase.port;

import config.DefaultConfiguration;
import implementation.Roll;

public class HelloWorldService {
	public static void main(String[] args) {
		/*
		 * 1. Prozess starten
		 * 2. Firefox-Plugin RESTEasy starten
		 * 3. Request-Typ auswaehlen und an http://localhost:ANGEZEIGTE_PORTNUMMER steht in der Console
		 * 4. In Firefox auf senden druecken
		 * 5. Response anschauen
		 */
		//get("/hello/:param1" , (request, response) -> "Hello, "+request.params(":param1") );
		
		port(DefaultConfiguration.JETTY_1_PORT);
		
		// our get 
		get("/dice/:param", (request, response) -> {
			int dice = new Integer(request.params(":param")); 
			return new Roll(dice * 2);
		});
	}
}