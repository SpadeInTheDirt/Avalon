/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */
 
class Minion extends BadPlayer{
	
	/** Creates a bad player with role of Minion, given a name.
	 *
	 * @param name, the name of the player.
	 */
	public Minion (String name) {
		super(name, "Minion");
	}
	
	/** Creates a bad player with role of Minion without a name.
	 */
	public Minion () {
		super("Minion");
	}
}