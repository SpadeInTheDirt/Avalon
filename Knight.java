/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */
 
class Knight extends GoodPlayer{
	
	/** Creates a good player with role of Knight, given a name.
	 *
	 * @param name, the name of the player.
	 */
	public Knight (String name) {
		super(new String[0], name, "Knight");
	}
	
	/** Creates a good player with role of Knight without a name.
	 */
	public Knight () {
		super(new String[0], "Knight");
	}
}