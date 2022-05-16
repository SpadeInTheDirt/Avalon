/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */

class BadPlayer extends Player{
	
	/** Creates a bad player given all unknown parameters for any given bad player.
	 *
	 * @param revealTypes, the array of strings containing all types of players to be revealed to this player.
	 * @param name, the name of the player.
	 * @param type, the type or role of this player.
	 */
	public BadPlayer (String[] revealTypes, String name, String type) {
		super(revealTypes, name, type, false);
	}
	
	/** Creates a bad player with a preset value for revealTypes, given a name and type.
	 *
	 * @param name, the name of the player.
	 * @param type, the type or role of this player.
	 */
	public BadPlayer (String name, String type) {
		super(new String[] {"Morgana", "Assassin", "Mordred", "Minion"}, type, false);
	}
	
	/** Creates a nameless bad player with a preset value for revealTypes, given a type.
	 *
	 * @param type, the type or role of this player.
	 */
	public BadPlayer (String type) {
		super(new String[] {"Morgana", "Assassin", "Mordred", "Minion"}, type, false);
	}
	
	/** Prompts a player to give a vote of Y or N to sabotoge a mission or do nothing, respectively.
	 * @return the player's decision to sabotoge a mission or not, returns true and false respectively.
	 */
	@Override
	public boolean sabotogeVote () {
		System.out.println("Enter Y to sabotoge and N to continue without sabotoging.");
		// use the player method vote to get a Y or N to true or false
		return this.vote();
	
	}
}