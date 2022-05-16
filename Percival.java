/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */
 
class Percival extends GoodPlayer{
	
	/** Creates a good player with role of Percival, given a name.
	 *
	 * @param name, the name of the player.
	 */
	public Percival (String name) {
		super(new String[] {"Morgana", "Merlin"}, name, "Percival");
	}
	
	/** Creates a good player with role of Percival without a name.
	 */
	public Percival () {
		super(new String[] {"Morgana", "Merlin"}, "Percival");
	}
	
	/** Prints out every player in the given PlayerList that has a type that is to be revealed to this player and reveals them as Merlin to this player.
	 *
	 * @param playerList, the list of players to reveal to this player.
	 */
	@Override
	public void revealOthers(PlayerList playerList) {
		
		Player[] players = playerList.getPlayers();
		
		String[] revealTypesTemp = this.getRevealTypes();
		String[] revealInfo;
		
		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < revealTypesTemp.length; j++) {
				
				if (revealTypesTemp[j].equals(players[i].getType())) {
					
					System.out.println(players[i] + " appears to be Merlin.");
					j = revealTypesTemp.length;
				}
			}
		}
	}
}