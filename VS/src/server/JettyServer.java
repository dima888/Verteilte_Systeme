package server;

import static spark.Spark.get;
import static spark.SparkBase.port;

import config.DefaultConfiguration;
import implementation.Roll;

/**
 * Starts our Rest-Services
 * @author Flah
 *
 */
public class JettyServer {

	public static void main(String[] args) {				
		// our get 
		get("/dice/:param", (request, response) -> {
			int dice = new Integer(request.params(":param")); 
			return new Roll(dice);
		});
	}
}
