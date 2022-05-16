/**
 * @author	Donald Lv
 * @version 1.0
 * @since	2021-04-18
 *
 */
 
import java.util.*;

abstract class Main {
	// game settings and constants
	public static final int ROUNDS = 5;
	public static final int TEAMATTEMPTSMAX = 5;
	public static final int FAILMISSIONCOUNTMAX = 3;
	public static final int SUCCESSMISSIONCOUNTMAX = 3;
	public static final int MINPLAYERS = 5;
	public static final int MAXPLAYERS = 10;
	
	public static final PlayerList BADEXPERIENCEDROLES = new PlayerList(new Player[] {new Oberon(), new Morgana(), new Mordred()});
	public static final PlayerList GOODEXPERIENCEDROLES = new PlayerList(new Player[] {new Percival()});
	
	public static final int[] GOODPLAYERSCOUNTS = {3, 4, 4, 5, 6, 6};
	public static final int[] BADPLAYERSCOUNTS = {2, 2, 3, 3, 3, 4};
	
	//5 lists of the varying counts of players for each of the 5 missions, one number for each round and playercount combination
	public static final int[][] MISSIONPLAYERSCOUNTS = {
		{2, 2, 2, 3, 3, 3},
		{3, 3, 3, 4, 4, 4},
		{2, 4, 3, 4, 4, 4},
		{3, 3, 4, 5, 5, 5},
		{3, 4, 4, 5, 5, 5}
	};
	
	//5 lists of the varying counts fail cards for each of the 5 missions to fail, one number for each round and playercount combination
	public static final int[][] FAILVOTECOUNTS = {
		{1, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 1},
		{1, 1, 2, 2, 2, 2},
		{1, 1, 1, 1, 1, 1}
	};

	public static void main(String[] args) {
		
		Scanner sn = new Scanner(System.in);
		
		boolean experienced = false;
		
		int numOfPlayers = -1;
		int failedMissions = 0;
		int successfulMissions = 0;
		
		String input = "";
		boolean inputCheck = false;
		
		
		
		//take input on how many players are playing
		System.out.println("How many players? Enter a number between 5 and 10.");
		
		if (sn.hasNextInt()) {
			numOfPlayers = sn.nextInt();
			//remove newline
			sn.nextLine();
		} else {
			//remove input
			sn.nextLine();
		}
		
		
		while(numOfPlayers < MINPLAYERS || numOfPlayers > MAXPLAYERS) {
			System.out.println("Invalid input, enter a number of players between 5 and 10.");
			
			if (sn.hasNextInt()) {
				numOfPlayers = sn.nextInt();
				//remove newline
				sn.nextLine();
			} else {
				//remove input
				sn.nextLine();
			}
		} 
		
		// what index to use in things like GOODPLAYERCOUNTS and others based on player count 
		int playerBasedIndex = numOfPlayers - MINPLAYERS;
		
		// clear input, ask for simple vs experienced mode
		input = "";
		System.out.println("Would you like to play the simple or experienced mode?");
		input = sn.nextLine();
		
		//invalid input, continually retry until valid input is given
		while(!input.toUpperCase().equals("SIMPLE") && !input.toUpperCase().equals("EXPERIENCED")) {
			//clear input, print error message, retry
			input = "";
			System.out.println("Invalid input, enter \"simple\" for simple mode or \"experienced\" for experienced mode.");
			input = sn.nextLine();
		}
		
		// set experienced to true or false based on input
		if (input.toUpperCase().equals("EXPERIENCED")) experienced = true;
		else experienced = false;


		//generate player roles
		
		//since good and bad players must have one assassin and one merlin, they begin as lists with just that role.
		PlayerList badPlayers = new PlayerList(new Player[] {new Assassin()});
		PlayerList goodPlayers = new PlayerList(new Player[] {new Merlin()});
		
		//retrieve the count of good and bad players from the constant values
		int goodPlayerCount = GOODPLAYERSCOUNTS[playerBasedIndex];
		int badPlayerCount = BADPLAYERSCOUNTS[playerBasedIndex];
		
		if (experienced) {
			//if the game is experienced, add all the good player experienced roles
			goodPlayers = goodPlayers.append(GOODEXPERIENCEDROLES).slice(0, goodPlayerCount);
			
			//randomize the bad experienced roles, add them to the list of bad players then truncate badPlayers appropriately - randomizes the experienced roles
			badPlayers = badPlayers.append(BADEXPERIENCEDROLES.shuffle()).slice(0, badPlayerCount);
			
		}
		
		//fill the rest of the good players with knights and the rest of the bad with minions
		while (goodPlayers.getPlayerCount() < goodPlayerCount) goodPlayers = goodPlayers.append(new Knight());
		while (badPlayers.getPlayerCount() < badPlayerCount) badPlayers = badPlayers.append(new Minion());
		
		// add all the good and bad players to all players then shuffle twice
		PlayerList allPlayers = goodPlayers.append(badPlayers).shuffle().shuffle();
		
		
		
		// begin the game
		
		//naming phase - prompt each player to name themselves
		for (int i = 0; i < numOfPlayers; i++) {
			
			//clear input, prompt for name input, take input
			input = "";
			System.out.println("Enter your name");
			input = sn.nextLine();
			
			//invalid input, continually retry until valid input is given
			while(input.length() < 2 || allPlayers.findPlayer(input) >= 0) {
				//clear input, provide error message, retake input
				input = "";
				System.out.println("Invalid input, enter a name that has not already been entered with two or more characters.");
				input = sn.nextLine();

			}
			
			//set current player's name
			allPlayers.getPlayers()[i].setName(input);
			
			clearScreen();
			
			// prompt for a next player for every player except the last
			if (i != numOfPlayers - 1) {
				System.out.println("Call the next player. Press enter to continue");
				sn.nextLine();
			}
		}
		
		//reveal phase
		System.out.println("Role and other players will be revealed this round.");
		promptNextPlayer(allPlayers.getPlayers()[0]);
		
		Player currentPlayer = allPlayers.getPlayers()[0];
		
		for (int i = 0; i < numOfPlayers; i++) {
			
			// print out player's info, then wait for acknowledgement
			System.out.println("Your role is " + currentPlayer.getType() + ".");
			currentPlayer.revealOthers(allPlayers);
			System.out.println("Press enter to continue.");
			
			// wait for acknowledgement, then clear
			sn.nextLine();
			clearScreen();
			
			// clear the screen then prompt the next player for all but the last
			if (i != numOfPlayers - 1) {
				//change the current player then prompt them
				currentPlayer = allPlayers.getPlayers()[i+1];				
				promptNextPlayer(currentPlayer);
			}
		}
		
		// pick a random player by their index in allPlayers
		Random rand = new Random();
		int proposingPlayerIndex = rand.nextInt(numOfPlayers);
		
		// create lists of players: the players on the team, those that accept the team and those that reject it
		PlayerList team = new PlayerList();
		PlayerList acceptedVotes = new PlayerList();
		PlayerList rejectedVotes = new PlayerList();
		
		// the player being proposed
		Player proposedPlayer;
		
		int votesForTeam = 0;
		
		//half is not a majority so (numOfPlayers)/2 +1 is used instead of (numOfPlayers+1)/2, where half would count as majority
		int requiredVotes = (numOfPlayers)/2 +1;
		
		int teamPlayerCount;
		int failedTeamAssemblyAttempts = 0;
		
		int sabotogeVotes = 0;
		int failureRequiredVotes;
		
		//game start message
		System.out.println("The game will begin.");
		
		//main game
		for (int round = 0; round < ROUNDS; round++) {
			
			// start failedTeamAssemblyAttempts at -1 so that it is zero on the first turn
			failedTeamAssemblyAttempts = 0;
			
			//update info according to round
			teamPlayerCount = MISSIONPLAYERSCOUNTS[round][playerBasedIndex]; 
			failureRequiredVotes = FAILVOTECOUNTS[round][playerBasedIndex];
			
			displayMissionInfo(round, playerBasedIndex);
			System.out.println("A team will be selected.");
			promptNextPlayer(allPlayers.getPlayers()[proposingPlayerIndex]);
			
			// keep proposing teams and switching the proposer until a proper team is found or it exceeds the maximum amount of tries
			do {
				//set team to empty to append players to it
				team = new PlayerList();
				
				// get the choosing player to pick teamPlayerCount amount of players to put in the team
				for (int i = 0; i < teamPlayerCount; i++) {
					
					//clear input, print list of players and prompt, then take input
					input = "";
					System.out.println();
					System.out.println("These are the players:");
					System.out.println(allPlayers);
					System.out.println("Type a player's name to add them to the team.");
					input = sn.nextLine();
					
					// while input is invalid, i.e. selected player cant be found in all the players or is found on the team, keep retrying input
					while (allPlayers.findPlayer(input) < 0 || team.findPlayer(input) >= 0) {
						
						System.out.println("Invalid player name, enter the name of a player that is not already on the team.");
						input = sn.nextLine();
					}
					
					// set the current proposed player to the one which has the same name as the input and append it to the team.
					proposedPlayer = allPlayers.getPlayers()[allPlayers.findPlayer(input)];
					team = team.append(proposedPlayer);
				}
				
				//reset the vote count and the lists of players who rejected/accepted
				votesForTeam = 0;
				acceptedVotes = new PlayerList();
				rejectedVotes = new PlayerList();
				
				//check with every player if the proposal is good
				for (int i = 0; i < allPlayers.getPlayerCount(); i++) {
					//clear the screen and prompt the next player
					clearScreen();
					promptNextPlayer(allPlayers.getPlayers()[i]);
					
					// display the mission specifics and the proposed team
					displayMissionInfo(round, playerBasedIndex);
					System.out.println("The proposed team is as such:");
					System.out.println(team);
					System.out.println("Enter Y if you accept this team and N if you do not.");
					
					// add a vote for the team if voting yes and add them to the acceptance list, otherwise add them to the rejection list and dont add to the votes for team
					if (allPlayers.getPlayers()[i].vote()) {
						votesForTeam ++;
						acceptedVotes = acceptedVotes.append(allPlayers.getPlayers()[i]);
					} else {
						rejectedVotes = rejectedVotes.append(allPlayers.getPlayers()[i]);
					}
				}
				
				// move to the next player after a proposal and vote (doesnt matter if vote failed or didnt, proposer changes)
				proposingPlayerIndex = (proposingPlayerIndex+1)%numOfPlayers;	
				
				
				clearScreen();
				
				//print out who voted for what, if none voted for/against print that none voted for/against, respectively
				if (acceptedVotes.getPlayerCount() > 0){
					System.out.println("These players voted for the team:");
					System.out.println(acceptedVotes);
				} else {
					System.out.println("No players voted for the team.");
				}
				
				if (rejectedVotes.getPlayerCount() > 0){
					System.out.println("These players voted against the team:");
					System.out.println(rejectedVotes);
				} else {
					System.out.println("No players voted against the team.");
				}
				
				System.out.println();
				
				//if the team failed to assemble, increase the amount of failedTeamAssemblyAttempts and print the fail message
				if (votesForTeam < requiredVotes) {
					failedTeamAssemblyAttempts ++;
					
					System.out.println("The vote failed with " + votesForTeam + " votes out of " + requiredVotes + " needed to succeed.");
					displayMissionInfo(round, playerBasedIndex);
					promptNextPlayer(allPlayers.getPlayers()[proposingPlayerIndex]);
				}				
			
			// while the team has not been accepted by a majority or there have been too many failures to create a team
			} while (votesForTeam < requiredVotes && failedTeamAssemblyAttempts < TEAMATTEMPTSMAX);
			
			
			
			// if the team did not fail to be created too many times
			if (failedTeamAssemblyAttempts < TEAMATTEMPTSMAX) {
				//reset sabotogeVotes
				sabotogeVotes = 0;
				
				// prompt the mission to begin
				System.out.println("The mission will begin.");
				promptNextPlayer(team.getPlayers()[0]);
				
				// go through the team and ask them if they sabotoge
				for (int i = 0; i < team.getPlayerCount(); i++) {
					
					// run the player's sabotoge vote
					if (team.getPlayers()[i].sabotogeVote()) sabotogeVotes ++;
					
					if (i != team.getPlayerCount() - 1) { //prompt the next player unless the player is last on the team
						clearScreen();
						promptNextPlayer(team.getPlayers()[i+1]);
					}
				}
				
				//clear screen, update successfulMissions and failedMissions and print mission results after all have voted
				clearScreen();
				
				if (sabotogeVotes >= failureRequiredVotes) {
					
					failedMissions ++;
					System.out.println("The mission failed. " + sabotogeVotes + " person/people sabotoged the mission.");
					
				} else {
					successfulMissions ++;
					System.out.println("The mission was a success.");
				}
				
			} else {
				//break if there were too many failures
				round = ROUNDS;
			}
			
			//check win conditions based on rounds and break when appropriate
			if (failedMissions >= FAILMISSIONCOUNTMAX) round = ROUNDS;
			else if (successfulMissions >= SUCCESSMISSIONCOUNTMAX) round = ROUNDS;
			
		}
		
		// if either the team failed to assemble too many times or too many missions failed
		if (failedTeamAssemblyAttempts == TEAMATTEMPTSMAX || failedMissions >= FAILMISSIONCOUNTMAX) {
			// print win screen for evil and reveal all player's roles
			System.out.println("The evil players won. These were the players' roles:");
			System.out.println(allPlayers.revealString());
			
		} else {
			// have the assassin guess for merlin
			int assassinIndex = -1;
			int assassinatedIndex = -1;
			
			//find the assassin
			for (int i = 0; i < allPlayers.getPlayerCount(); i ++) {
				if (allPlayers.getPlayers()[i].getType().equals("Assassin")) {
					assassinIndex = i;
					i = allPlayers.getPlayerCount();
				}
			}
			
			// prompt message
			clearScreen();
			System.out.println("Three missions were successful.");
			System.out.println(allPlayers.getPlayers()[assassinIndex] + " was the assassin. They will now guess who Merlin was.");
			sn.nextLine();
			
			System.out.println();
			System.out.println("These are the players:");
			System.out.println(allPlayers);
			System.out.println("Type a player's name to assassinate.");
			input = sn.nextLine();
			
			// while input is invalid, i.e. selected player cant be found or the selected player is assassin
			while (allPlayers.findPlayer(input) < 0 || allPlayers.findPlayer(input) == assassinIndex) {
				//clear input
				input = "";
				System.out.println("Invalid player name, enter the name of a player.");
				input = sn.nextLine();
			}
			
			//find the player the assassin assassinated
			assassinatedIndex = allPlayers.findPlayer(input);
			
			
			System.out.println();
			// if merlin was assassinated, print the win screen for evil players, otherwise print the win screen for good players and reveal the players
			if (allPlayers.getPlayers()[assassinatedIndex].getType().equals("Merlin")) {
				System.out.println("Merlin was assassinated and the evil players won. These were the players' roles:");
				System.out.println(allPlayers.revealString());
			} else {
				System.out.println("The good players won. These were the players' roles:");
				System.out.println(allPlayers.revealString());
			}
		}
		
	}
	
	/** Clears the screen by printing 100 lines.
	 */
	public static void clearScreen() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
	
	/** Prompts for the next player to come to play their turn.
	 *
	 * @param player, the player to be prompted to play their turn.
	 */
	public static void promptNextPlayer(Player player) {
		Scanner sn = new Scanner(System.in);
		
		System.out.println("Call " + player + " for their turn. Press enter to start the turn.");
		sn.nextLine();
		System.out.println();
	}
	
	/** Prints information about the current round's mission, i.e. the number of players on the mission and how many sabotoge votes it requires.
	 *
	 * @param round, the current round of the game.
	 * @param playerBasedIndex, the index in subject constant values to take value from, based on the number of players playing.
	 */
	public static void displayMissionInfo (int round, int playerBasedIndex) {
		System.out.println("The next mission has a team of " + MISSIONPLAYERSCOUNTS[round][playerBasedIndex] + " players and requires at least " + FAILVOTECOUNTS[round][playerBasedIndex] + " sabotoge(s) to fail.");
	}
}