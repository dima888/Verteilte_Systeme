package implementation;

/**
 * This class is our transaction 
 * @author foxhound
 *
 */
public class Transaction {

	private String from = "";
	private String to = "";
	private String reason = "";
	
	
	/**
	 * Method tranfer a amount from the bank, to our player account
	 * @param bank - a bank object from a game
	 * @param account - a player account
	 * @param amount - transfer amount
	 * @param reason - why you do a transaction?
	 * @return boolean
	 */
	public boolean transfer(Bank bank, Account account, int amount, String reason) {
		// TODO: 
		return false;
	}
	
	/**
	 * Method transfer a mount from a account to a other account
	 * @param accountFrom - sub money
	 * @param accountTo - add money
	 * @param amount - +- money value
	 * @param reason - why you do why you do a transaction?
	 * @return boolean 
	 */
	public boolean transfer(Account accountFrom, Account accountTo, int amount, String reason) {
		// TODO: 
		return false;
	}
	
	/**
	 * Helper method for setting properties
	 */
	private void setProperties() {
		// TODO: 
	}
}
