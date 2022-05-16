/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */
 
class Assassin extends BadPlayer{
	
	/** Creates a bad player with role of Assassin, given a name.
	 *
	 * @param name, the name of the player.
	 */
	public Assassin (String name) {
		super(name, "Assassin");
	}
	
	/** Creates a bad player with role of Assassin without a name.
	 */
	public Assassin () {
		super("Assassin");
	}
}