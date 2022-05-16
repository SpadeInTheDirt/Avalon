/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */

import java.util.Scanner;

class GoodPlayer extends Player{
	
	/** Creates a good player given all unknown parameters for any given good player.
	 *
	 * @param revealTypes, the array of strings containing all types of players to be revealed to this player.
	 * @param name, the name of the player.
	 * @param type, the type or role of this player.
	 */
	public GoodPlayer (String[] revealTypes, String name, String type) {
		super(revealTypes, name, type, true);
	}
	
	/** Creates a nameless good player, given a value for revealTypes and type.
	 *
	 * @param revealTypes, the array of strings containing all types of players to be revealed to this player.
	 * @param type, the type or role of this player.
	 */
	public GoodPlayer (String[] revealTypes, String type) {
		super(revealTypes, type, true);
	}
	
	/** Prompts the user to continue. Returns false, as the player is good, and incapable of sabotoge.
	 * @return the player's decision to sabotoge the mission or not. Always returns false, a good players cannot sabotoge.
	 */
	@Override
	public boolean sabotogeVote () {
		Scanner sn = new Scanner(System.in);
		
		System.out.println("You cannot sabotoge. Press enter to continue.");
		sn.nextLine();
		return false;
	}
}