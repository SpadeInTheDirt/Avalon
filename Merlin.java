/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */

class Merlin extends GoodPlayer{
	
	/** Creates a good player with role of Merlin, given a name.
	 *
	 * @param name, the name of the player.
	 */
	public Merlin (String name) {
		super(new String[] {"Morgana", "Assassin", "Oberon", "Minion"}, name, "Merlin");
	}
	
	/** Creates a good player with role of Merlin without a name.
	 */
	public Merlin () {
		super(new String[] {"Morgana", "Assassin", "Oberon", "Minion"}, "Merlin");
	}
}