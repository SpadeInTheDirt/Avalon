/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */
 
class Oberon extends BadPlayer{
	
	/** Creates a bad player with role of Oberon, given a name.
	 *
	 * @param name, the name of the player.
	 */
	public Oberon (String name) {
		super(name, "Oberon");
	}
	
	/** Creates a bad player with role of Oberon without a name.
	 */
	public Oberon () {
		super("Oberon");
	}
}