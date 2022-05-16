/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */
 
class Mordred extends BadPlayer{
	
	/** Creates a bad player with role of Mordred, given a name.
	 *
	 * @param name, the name of the player.
	 */
	public Mordred (String name) {
		super(name, "Mordred");
	}
	
	/** Creates a bad player with role of Mordred without a name.
	 */
	public Mordred () {
		super("Mordred");
	}
}