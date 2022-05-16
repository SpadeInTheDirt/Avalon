/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */
 
class Morgana extends BadPlayer{
	
	/** Creates a bad player with role of Morgana, given a name.
	 *
	 * @param name, the name of the player.
	 */	
	public Morgana (String name) {
		super(name, "Morgana");
	}
	
	/** Creates a bad player with role of Morgana without a name.
	 */
	public Morgana () {
		super("Morgana");
	}
}