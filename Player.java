/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */

import java.util.Scanner;

abstract class Player {

	private String[] revealTypes;
	public String name;
	private String type;
	private boolean isGood;
	
	/** Creates a player with the given reveal types, name, type and alignment (whether they are good or evil).
	 *
	 * @param revealTypes, the array of strings containing all types of players to be revealed to this player.
	 * @param name, the name of the player.
	 * @param type, the type or role of this player.
	 * @param isGood, whether the player is good or not.
	 */
	public Player (String[] revealTypes, String name, String type, boolean isGood) {
		this.name = name;
		this.type = type;
		this.isGood = isGood;
		this.revealTypes = revealTypes;
	}
	
	/** Compares whether the player is equal to another.
	 * @return whether the player is equal to another.
	 *
	 * @param player, the player to compare agains
	 */
	public Player (String[] revealTypes, String type, boolean isGood) {
		this.name = "";
		this.type = type;
		this.isGood = isGood;
		this.revealTypes = revealTypes;
	}
	
	/** Compares whether the player is equal to another.
	 * @return whether the player is equal to another.
	 *
	 * @param player, the player to compare against.
	 */
	public boolean equals (Player player) {
		//players are the same if their capitalized names are the same, return true if they are
		if (player.getName().toUpperCase().equals(this.name.toUpperCase())) return true;
		// else, return false
		return false;
	}
	
	/** Returns the player as a string value.
	 * @return the player as a string value, i.e. the name of the player.
	 */
	public String toString() {
		return this.name;
	}
	
		
	/** Get the value of this player's variable name.
	 * @return the name of the player.
	 */
	public String getName() {
		return this.name;
	}
	
	/** Set the player's variable name to the given value.
	 *
	 * @param name, the string to set this player's value to.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** Get the value of this player's variable type.
	 * @return the type/role of the player.
	 */
	public String getType() {
		return this.type;
	}
	
	/** Set the player's variable type to the given value.
	 *
	 * @param type, the string to set this player's value to.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/** Get the value of this player's variable revealTypes.
	 * @return the array of types of player to reveal to player.
	 */
	public String[] getRevealTypes() {
		return this.revealTypes;
	}
	
	/** Set the player's variable revealTypes to the given value.
	 *
	 * @param revealTypes, the array of strings to set this player's value to.
	 */
	public void setRevealTypes(String[] revealTypes) {
		this.revealTypes = revealTypes;
	}
	
	/** Get the value of this player's variable isGood.
	 * @return whether the player is good or evil (returns true for good and false otherwise).
	 */
	public boolean getIsGood() {
		return this.isGood;
	}
	
	/** Set the player's variable isGood to the given value.
	 *
	 * @param isGood, the boolean to set this player's value to.
	 */
	public void setIsGood(boolean isGood) {
		this.isGood = isGood;
	}
	
	/** Reveals every player from playerList whos type matches any of the player's types to reveal. 
	 * 
	 * @param playerList, the list of players to reveal from.
	 */
	public void revealOthers(PlayerList playerList) {
		
		Player[] players = playerList.getPlayers();
		boolean foundPlayers = false;
			
		//for every player, check the player against all this player's reveal types
		for (int i = 0; i < players.length; i++) {
			// if the player being checked isnt the same as this player, check the player
			if (!this.equals(players[i])) {
				
				// check the player against all of the player's revealTypes
				for (int j = 0; j < this.revealTypes.length; j++) {
					
					// if the player is found to have a type belonging in this player's revealTypes, print their alignment (whether they are good or evil), set that a player was revealed and quit checking against types
					if (this.revealTypes[j].equals(players[i].getType())) {
						System.out.println(players[i] + " is " + (players[i].getIsGood() ? "good" : "evil") + ".");
						foundPlayers = true;
						j = this.revealTypes.length;
					}
				}
			}
		}
		
		// if no players were revealed
		if (!foundPlayers) System.out.println("There are no players revealed to you.");
	}
	
	/** Continuously take input from the player until they respond with Y or N. Return true for if they responded Y and false for N. 
	 * @return whether the player votes yes or no, true for Y and false for N
	 */
	public boolean vote() {
		Scanner sn = new Scanner(System.in);
		
		String input = sn.nextLine();
		
		//check if input was y or n, continually retry until valid input is given
		while(!input.toUpperCase().equals("Y") && !input.toUpperCase().equals("N")) {
			
			System.out.println("Invalid input, enter Y or N.");
			input = sn.nextLine();

		}
		
		// return true for an input of y else they must have responded n, so return false
		if (input.toUpperCase().equals("Y")) return true;
		return false;
	}
	
	/** Prompts the user to continue or to sabotoge. Returns false if the player does nothing and true if the player sabotoges.
	 * @return whether the player sabotoged the mission or not.
	 */
	abstract boolean sabotogeVote ();
}