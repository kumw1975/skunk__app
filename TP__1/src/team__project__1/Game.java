package team__project__1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
	
		private int 	 goal;
		private int 	 roundNumber;
		private int 	 numberOfChipsInKitty;
		private int 	 activePlayerLoc;
		private int 	 doubleSkunkCount;
		private Player 	 activePlayer;	
		private Player[] players;
		private int 	 numberOfPlayers;
		private boolean  isLastRound;
		private Player[] lastRoundSequence;
	
	
	public Game() {		
		
		this.goal 					= 100; 
		this.roundNumber  			= 0; 
		this.numberOfChipsInKitty 	= 0; 
		this.activePlayerLoc 		= 0;
		this.doubleSkunkCount 		= 0;
		this.isLastRound 			= false;
		//setup(); //uncomment in production
		
		//Start Testing (Dev) Snippet
		this.players = new Player[]{new Player("MO"), new Player("HO"), new Player("DO")};
		this.numberOfPlayers = players.length;
		
		System.out.println("------------------------------------------------------");
		System.out.println("Player Info\n------------------------------------------------------");
		for (int i = 0; i < players.length; i++) {	
			players[i].setPlayerNumber(i+1);
			String name = (  (i+1) < 10 ) ? " "+(i+1)+" : "+players[i].getName() : (i+1)+" : "+players[i].getName();
			System.out.println("Player "+ name);
		}
		setActivePlayerToNextPlayer();
		//End Testing (Dev) Snippet
	}
	
	
	public void setActivePlayerToNextPlayer() {
		
		this.activePlayer 		= players[activePlayerLoc%players.length];
		if(activePlayerLoc%players.length ==0) {
			startNewRound();			
		}
		this.activePlayerLoc	= activePlayerLoc+1;

	}
	
	public void startNewRound(){
		
		if(! this.isLastRound()) {
			incrementRoundNumber();				
		}
		else{
			
		}
	}
	
	
	
	public void updatePlayerMetrics(String penalty){
		
		
		Player activePlayer   = this.getActivePlayer();
		String penaltyDetails = "";
		
		if(penalty.equalsIgnoreCase("OneSkunk")){
	
			this.resetActivePlayersRoundPoints();	
			activePlayer.takeNumberOfChips(1);
		 	this.addChipsToKittyFromActivePlayer(1);	
			
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Rolled One Skunk ::" + activePlayer.showRollDetails()+"\n");
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Lost: A turn, all round points &  1 chip (added to kitty)");
		 	penaltyDetails = penaltyDetails + ("\n******************************************************");			 	
		}
		
		else if(penalty.equalsIgnoreCase("TwoSkunks")){

			activePlayer.setTurnsTakenInCurrentRound(activePlayer.getTurnsTakenInCurrentRound()+1);		
			this.resetActivePlayersRoundPoints();			
			activePlayer.takeNumberOfChips(4);			
		 	this.addChipsToKittyFromActivePlayer(4);	
		 	
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Rolled Two Skunks ::" + activePlayer.showRollDetails()+"\n");
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Lost: A turn, all game points &  4 chips (added to kitty)");
		 	penaltyDetails = penaltyDetails + ("\n******************************************************");	

			
		}
		
		else if(penalty.equalsIgnoreCase("SkunkAndDeuce")){
			
			activePlayer.setTurnsTakenInCurrentRound(activePlayer.getTurnsTakenInCurrentRound()+1);		
			this.resetActivePlayersRoundPoints();			
			activePlayer.takeNumberOfChips(2);			
		 	this.addChipsToKittyFromActivePlayer(2);	
 		 	
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Rolled One Skunk and a Deuce ::" + activePlayer.showRollDetails()+"\n");
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Lost: A turn, all round points &  2 chips (added to kitty)");
		 	penaltyDetails = penaltyDetails + ("\n******************************************************");	

		}
		
		else{
			
			activePlayer.incrementTotalTurnsTaken();
			activePlayer.setTurnsTakenInCurrentRound(activePlayer.getTurnsTakenInCurrentRound()+1);		
			activePlayer.setTurnPoints(activePlayer.getTurnPoints()+activePlayer.getRollValue());
			activePlayer.setRoundPoints(activePlayer.getRoundPoints()+activePlayer.getRollValue());	
			activePlayer.setGamePoints(activePlayer.getGamePoints()+activePlayer.getRollValue());
		}
		
		
		if(activePlayer.getGamePoints() > this.getGoal()){
			this.setGoal(activePlayer.getGamePoints());
			System.out.println("******************************************************");
			System.out.println("******************************************************");
			System.out.println("******************************************************");
			System.out.println("******************************************************");
			System.out.println("******************************************************");
			System.out.println("New Highest Score: " + activePlayer.getGamePoints() +" set by "+ activePlayer.getName());
			System.out.println("******************************************************");
			System.out.println("******************************************************");
			System.out.println("******************************************************");
			System.out.println("******************************************************");
			System.out.println("******************************************************");
			System.out.println("------------------------------------------------------");			
			this.setLastRound();
		}
		
		if (penalty.trim().length()>0) {
			activePlayer.incrementTotalTurnsTaken();
		 	this.setActivePlayerToNextPlayer();

			System.out.println("************** THE PENALTY IS "+ penalty + " ************** ");
			System.out.println(penaltyDetails);
			
		}
	}
	
	

	private void addChipsToKittyFromActivePlayer(int i) {
		this.addChipsToKitty(i);
		
	}


	private void resetActivePlayersRoundPoints() {
		this.getActivePlayer().setRoundPoints(0);
		
	}


	public String analyzeDiceValues() {

		String result = "";
		Player activePlayer = this.getActivePlayer();
		
		if ((activePlayer.getDie1RollValue()==1 && activePlayer.getDie2RollValue()>2  ) 
		|| ( activePlayer.getDie1RollValue()>2  && activePlayer.getDie2RollValue()==1)){
	
			result = "OneSkunk";

		}				
		
		//roll (1 and 2) -> rolled 1 skunk and 1 deuce
		if ((activePlayer.getDie1RollValue()==1 && activePlayer.getDie2RollValue()==2 ) 
		|| ( activePlayer.getDie1RollValue()==2 && activePlayer.getDie2RollValue()==1)){
			
			result = "SkunkAndDeuce";

		}
		
		//roll (1 and 1) -> rolled 2 skunks
		if ((activePlayer.getRollValue() == 2)){
						
			result = "TwoSkunks";
		}
		
		return result;
	}	

	
	public void addChipsToKitty(int numberOfChipsToAddToKitty) {
		this.numberOfChipsInKitty = this.numberOfChipsInKitty+ numberOfChipsToAddToKitty;
	}	
	
	public void setGoal(int goal) {
		this.goal = goal;
	}
	
	private void setPlayers(Player[] playersArray) {
		this.players = playersArray;		
	}
	
	public void setLastRound() {		

		Player[] players  			= this.getPlayers();
		Player[] lastRoundSequence 	= new Player[players.length];

		int cap			 			= players.length;				
		int playerLoc 	 			= getLoc(this.getActivePlayer());
		
		for (int i = 0; i < cap; i++) {
			lastRoundSequence[i] 	= players[playerLoc%cap];
			playerLoc++;
		}
		this.lastRoundSequence 		= lastRoundSequence;
		this.isLastRound 			= true;		
	}
	
	private int getLoc(Player player){		
		int cap = this.getPlayers().length;
		int loc = 0;		
		for (int i = 0; i < cap; i++) {
			if (this.getPlayers()[i].equals(player)) {
				loc = i;
			}
		}
		return loc;
	}
	
	public void incrementRoundNumber() {
		this.roundNumber++;
	}	
		
	public void incrementDoubleSkunkCount() {
		this.doubleSkunkCount++;		
	}	
	
	
	
	
	
	public int getNumberOfChipsInKitty() {
		return numberOfChipsInKitty;
	}

	public int getRollValue() {
		return getActivePlayer().getRollValue();
	}
	
	public int getDie1RollValue() {
		return getActivePlayer().getDie1RollValue();
	}
	
	public int getDie2RollValue() {
		return getActivePlayer().getDie2RollValue();
	}
	
	public int getDoubleSkunkCount() {
		return this.doubleSkunkCount;
	}
	
	public Player getActivePlayer() {
		//System.out.println(this.activePlayer);
		return this.activePlayer;
	}

	public Player[] getPlayers() {
		return this.players;		
	}

	public boolean isLastRound() {
		return isLastRound;
	}	
	
	public int getRoundNumber() {
		return this.roundNumber;
	}
		
	public int getGoal() {
		return this.goal;
	}





	
	
	
	
 	// gets user input and sets number of players, initializes the player array, sets active player 
	private void setup() {
		
		System.out.println("Enter the number of players");
		this.numberOfPlayers = getNumberOfPlayers();
		System.out.println("There are "+ this.numberOfPlayers + " players");
		
		this.players = new Player[this.numberOfPlayers];
		
		for (int i = 0; i < this.players.length; i++) {
			System.out.println("Enter Player "+ (i+1) +"'s username ");
			String playerName = getPlayerName();
			this.players[i] = new Player(playerName.toUpperCase());
			this.players[i].setPlayerNumber(i+1);
			System.out.println("Player "+  (i+1)  +"'s username is "+ playerName);
		}
		
		System.out.println("\n------------------------------------------------------");
		System.out.println("Player Info\n------------------------------------------------------");
		for (int i = 0; i < players.length; i++) {			
			String name = (  (i+1) < 10 ) ? " "+(i+1)+" : "+players[i].getName() : (i+1)+" : "+players[i].getName();
			System.out.println("Player "+ name);
		}
		setPlayers(this.players);
		setActivePlayerToNextPlayer();
	}
	
	
	private static int getNumberOfPlayers() {//from user as userInput	
		
		int result				= 0;		
		String input 			= ""; 
		BufferedReader reader 	= null;		

		try {			
			reader = new BufferedReader(new InputStreamReader(System.in));
			input  = reader.readLine();
			result = Integer.parseInt(input.trim());
			
		} catch (NumberFormatException d) {			
			System.err.println("WARNING: Wrong Input format!!! Enter NUMBERS ONLY");
			System.out.println("Enter the number of players greater than 0");
			result = getNumberOfPlayers();
		}
		catch (IOException ioe) {			
			System.err.println("WARNING: Invalid Input !!!");
			System.out.println("Enter the number of players");
			result = getNumberOfPlayers();
		}	
		
		while (result < 2 || result > 8) {			
			System.err.println("GAME RULE VIOLATION: #Players >=2 && <=8");
			System.out.println("Enter the number of players");
			result = getNumberOfPlayers();			
		}		
		return result;
	}	
	
	
	private static String getPlayerName(){
		
		String input 			= ""; 
		BufferedReader reader 	= null;
		
		try {			
			reader = new BufferedReader(new InputStreamReader(System.in));
			input  = reader.readLine();
			
		}catch (Exception d) {}

		while (input.trim().length() < 1) {			
			System.err.println("GAME RULE VIOLATION: Username can not be empty");
			System.out.println("Enter player username ");
			input = getPlayerName();			
		}		
		return input;
	}


	public Player[] getLastRoundSequence() {
		return this.lastRoundSequence;		
	}



}
