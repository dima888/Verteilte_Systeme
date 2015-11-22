package config;

public class DefaultConfiguration {

	public static final String HOST = "//localhost/";
	
	public static final String RMI_ID = "dice";
	public static final int RMI_PORT = 7764;
	
	public static final int JETTY_1_PORT = 5000;
	public static final int JETTY_2_PORT = 5001;
	
	public static final String RESPONSE_TYPE_JSON = "application/json";
	
	public static final String DB_URL_WRITE = "http://192.168.99.102:4567/db/write/";
	public static final String DB_URL_READ = "http://192.168.99.102:4567/db/read/";
	
//	public static final String DB_URL_WRITE = "http://localhost:7777/db/write/";
//	public static final String DB_URL_READ = "http://localhost:7777/db/read/";
}
