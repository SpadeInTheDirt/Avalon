/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */

import java.util.Random;

class PlayerList {
	
	private Player[] players;
	private int playerCount;
	
	/** Creates a PlayerList object from an array of players.
	 *
	 * @param players, the array of players.
	 */
	public PlayerList (Player[] players) {
		this.players = players;
		this.playerCount = players.length;
	}
	
	/** Creates a PlayerList with an empty array of players.
	 */
	public PlayerList () {
		this.players = new Player[0];
		this.playerCount = 0;
	}
	
	/** Compares two PlayerLists and returns whether they are equal.
	 * @return whether every player in the playerList is equal to the player at the same index in this PlayerList.
	 *
	 * @param playerList, the PlayerList to compare to.
	 */
	public boolean equals (PlayerList playerList) {
		// if the counts in each player list are the same
		if (this.playerCount == playerList.getPlayerCount()) {
			
			// if all the players are equal at each index
			for (int i = 0; i < this.playerCount; i++) {
				if (!this.players[i].equals(playerList.getPlayers()[i])) return false;
			}
			
			// if they were all equal, this method wouldnt have finished yet, so would return true
			return true;
			
		}
		// if they werent the same length, return false
		else return false;
	}
	
	/** Returns the PlayerList as a string.
	 * @return a string listing the names of all of the players in the PlayerList.
	 */
	public String toString() {
		// no players in the list, return empty string
		if (this.playerCount < 1) return "";
		else {
			// else concatenate all the players' strings, comma seperated with no comma at the end
			String tempString = this.players[0].toString();
			
			for (int i = 1; i < this.playerCount; i++) {
				tempString += "\n" + this.players[i];
			}
			
			return tempString;
		}
	}
	
	/** Returns the PlayerList as a string that includes their role.
	 * @return a string listing the names of all of the players in the PlayerList as well as their types/roles.
	 */
	public String revealString() {
		// no players in the list, return empty string
		if (this.playerCount < 1) return "";
		else {
			// else concatenate all the players' strings, comma seperated with no comma at the end
			String tempString = this.players[0].toString() + " had the role of " + this.players[0].getType();
			
			for (int i = 1; i < this.playerCount; i++) {
				tempString += "\n" + this.players[i] + " had the role of " + this.players[i].getType() + ".";
			}
			
			return tempString;
		}
	}
	
	/** Returns the array of players in the PlayerList.
	 * @return a string listing the names of all of the players in the PlayerList.
	 */
	public Player[] getPlayers() {
		return this.players;
	}
	
	/** Sets the array of players in the PlayerList to the given value.
	 *
	 * @param players, the array of players to set this PlayerList to.
	 */
	public void setPlayers(Player[] players) {
		this.players = players;
		this.playerCount = players.length;
	}
	
	/** Returns the number of players in the PlayerList.
	 * @return the number of players in the PlayerList.
	 */
	public int getPlayerCount() {
		return this.playerCount;
	}
	
	/** Returns a PlayerList of the player appended to the current list of players.
	 * @return the PlayerList containing the player appended to the current list of players.
	 *
	 * @param player, the player to append to the current PlayerList.
	 */
	public PlayerList append (Player player) {
		//make newPlayers have space for all of the current players and the new one
		Player[] newPlayers = new Player[this.playerCount + 1];
		
		//copy all the values from current players
		for (int i = 0; i < this.playerCount; i++) newPlayers[i] = this.players[i];
		
		//put the new player at the end of newPlayers then return a new playerlist with the new list of players
		newPlayers[this.playerCount] = player;
		return new PlayerList(newPlayers);
	}
	
	/** Returns a PlayerList of all of the players in the given PlayerList appended to the current list of players.
	 * @return the PlayerList containing all of the players in the given PlayerList appended to the current list of players.
	 *
	 * @param playerList, the list of players to append to the current PlayerList.
	 */
	public PlayerList append (PlayerList playerList) {
		//make newPlayers have space for all of the current players and the other players
		Player[] otherPlayers = playerList.getPlayers();
		Player[] newPlayers = new Player[this.playerCount + playerList.getPlayerCount()];
		
		// copy all the values of current players to the new list
		for (int i = 0; i < this.playerCount; i++) newPlayers[i] = this.players[i];
		
		// then copy all the other players to the new list
		for (int i = 0; i < playerList.playerCount; i++) newPlayers[i + this.playerCount] = otherPlayers[i];
		
		//return a new playerlist with the new list of players
		return new PlayerList(newPlayers);
	}
	
	/** Returns a randomized version of the current PlayerList.
	 */
	public PlayerList shuffle () {
		Random rand = new Random();
		
		// make a copy of players to shuffle
		Player[] newPlayers = new Player[this.playerCount];
		
		for (int i = 0; i < this.playerCount; i++) newPlayers[i] = this.players[i];
		
		//temporary slot for swapping players in Player[] players
		Player tempSwappingPlayer;
		int swappingIndex;
		
		//randomly swap each player with another
		for (int i = 0; i < this.playerCount; i ++) {
			//pick a random index in newplayers
			swappingIndex = rand.nextInt(this.playerCount);
			
			//swap the value at i and the random index
			tempSwappingPlayer = newPlayers[i];
			newPlayers[i] = newPlayers[swappingIndex];
			newPlayers[swappingIndex] = tempSwappingPlayer;
		}
		
		return new PlayerList(newPlayers);
	}
	
	/** Returns a PlayerList containing the players from the given start index to one before the ending index in the current PlayerList.
	 * @return the sliced PlayerList from startIndex to one befor
	 *
	 * @param startIndex, the starting index to return players from this PlayerList.
	 * @param endIndex, the end index to end the return before from this PlayerList.
	 */
	public PlayerList slice(int startIndex, int endIndex) {
		//function works the same as substring
		
		// ensure start and end are in bounds
		if (startIndex < 0) startIndex = 0;
		if (startIndex > this.playerCount) startIndex = this.playerCount;
		
		if (endIndex < 0) endIndex = 0;
		if (endIndex > this.playerCount) endIndex = this.playerCount;
		
		//the length of the new array is end - start: begins at start and ends 1 before end
		Player[] newPlayers = new Player[endIndex - startIndex];
		
		// copy values from the current player list to newPlayers from start index to 1 before the end
		for (int i = startIndex; i < endIndex; i++) newPlayers[i - startIndex] = players[i];
		
		// return a playerlist with the new list of players
		return new PlayerList(newPlayers);
		
	}
	
	/** Returns the lowest index of a player in this PlayerList whos name matches the given name.
	 * @return the first index at which a player was found with the given name, regardless of case.
	 *
	 * @param name, the name of the player being searched for.
	*/
	public int findPlayer (String name) {
		
		// search through the player list for the name
		for (int i = 0; i < this.playerCount; i++) {
			// if a match was made, return the index to quit searching
			if (this.players[i].getName().toUpperCase().equals(name.toUpperCase())) {
				return i;
			}
		}
		
		//nothing found, no early quit, return -1
		return -1;
	}
	
	/** Returns the lowest index of a player in this PlayerList that matches the given player.
	 * @return the first index at which a player in this PlayerList was found that matches the given player.
	 *
	 * @param player, the player being searched for.
	*/
	public int findPlayer (Player player) {
		
		// search through the player list for the player
		for (int i = 0; i < this.playerCount; i++) {
			// if a match is made, return the index to quit searching
			if (this.players[i].equals(player)) {
				return i;
			}
		}
		
		//nothing found, no early quit, return -1
		return -1;
	}
}