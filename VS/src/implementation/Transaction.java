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
	 * Bank pull a amount from a player
	 * @param bank - a bank object from a game
	 * @param account - a player account
	 * @param amount - transfer amount
	 * @param reason - why you do a transaction?
	 * @return boolean
	 */
	public boolean transferPull(Bank bank, Account account, int amount, String reason) {
		
		int bankMoney = bank.getBankAmount();		
		int playerMoney = account.getSaldo();
		
		// pull money from a player account
		boolean successSetPlayerMoney = account.setSaldo(playerMoney - amount);
		
		// condition
		if ( !successSetPlayerMoney ) {
			return false;
		}
		
		// push the money to our bank
		boolean successSetBankMoney = bank.setBankAmount(bankMoney + amount);
		
		// condition
		if ( !successSetBankMoney ) {
			return false;
		}
		return true;
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
