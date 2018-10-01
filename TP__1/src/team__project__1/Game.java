package team__project__1;
import java.io.*;

public class Game {
	
	/*
	 
	 start Game:: USERINPUT ::=> #players, player names
	 	initialize Players
	 	
	 	start Round()
	 		initialize Round Metrics
	 			Start Turn()
	 				initialize Turn Metrics
	 				roll()
	 				analyze dice values
	 				update Turn Metrics
	 				askToRollAgain()
	 					if y:go to roll() else: go to endTurn()
	 			end Turn() :: 
	 		Update Round Metrics
	 	end Round()
	 	
	 	get Winner()
	 	displayChipDistributionOptions()
	 	distributeChips()
	 
	 
	 */
	
	
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
	
	public Game(){
		
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
		System.out.println("PLAYER INFO\n------------------------------------------------------");
		for (int i = 0; i < players.length; i++) {	
			players[i].setPlayerNumber(i+1);
			String name = (  (i+1) < 10 ) ? " "+(i+1)+" : "+players[i].getName() : (i+1)+" : "+players[i].getName();
			System.out.println("Player "+ name);
		}
		//Simulate last round
		players[1].setGamePoints(100);
		players[1].setPreviousMetrics();

		//End Testing (Dev) Snippet		
	}
	
	
	public void startRound(){
		//initialize Round metrics
 		roundNumber++;
		for (int i = 0; i < players.length; i++) {	
			players[i].setRoundPoints(0);
			players[i].setTurnsTakenInCurrentRound(0);
		}
		activePlayerLoc = 0;
	}
	
	public void startTurn(){
		
		if (!isLastRound) {
			activePlayer = players[activePlayerLoc];
		}
		else{
			activePlayer = players[activePlayerLoc];
			System.out.println(activePlayerLoc+ " ACTIVE PLAYER SET TO "+ activePlayer.getName());
		}		
		
		//inititalize Turn Metrics
		activePlayer.setTurnPoints(0);
		activePlayer.setTurnsTakenInCurrentRound(0); 
		
		String penalty 			= "";
		BufferedReader reader 	= null;
		String input 			= "Y";		
		String status 			= "";		
		
		while(penalty.trim().length() == 0 && input.trim().equalsIgnoreCase("Y")){
			try {	
				activePlayer.roll();

				int previousNumberOfChipsInKitty = numberOfChipsInKitty;
				
				status = "ROUND " + roundNumber+ " TURN " +(activePlayer.getTurnsTakenInCurrentRound()+1);
				status = status   + " FOR "+ activePlayer.getName() + " ****** " + activePlayer.getName() +" ROLLED \t::"+ activePlayer.getRollValue();
				status = status   + " => " + activePlayer.getDie1RollValue() +" + "+  activePlayer.getDie2RollValue();

				System.out.println("******************************************************");
				System.out.println(status);				
				System.out.println("******************************************************");

				penalty = analyzeDiceValues();
				updateTurnMetrics(penalty);
				
				System.out.println("------------------------------------------------------");
				System.out.println("GAME INFO \t\t\t\t: OLD \t=> NEW" );
				System.out.println("------------------------------------------------------");
				System.out.println("NUMBER OF CHIPS IN THE GAME'S KITTY \t: " + previousNumberOfChipsInKitty +" \t=> "+  numberOfChipsInKitty);
				
				System.out.println(activePlayer);
				
				if(penalty.trim().length() == 0 ) {
					System.out.println(activePlayer.getName()+ ", Would you like to roll again? -> Enter Y/N");
					reader 	= new BufferedReader(new InputStreamReader(System.in));
					input 	= reader.readLine();					
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
		activePlayerLoc++;	
	}
	
	public void play(){		
		while (!isLastRound) {
			startRound();
			for (int i = 0; i < players.length; i++) {
				startTurn();
				if(activePlayer.getGamePoints() >= goal){
					goal =(activePlayer.getGamePoints());
					System.out.println("******************************************************");
					System.out.println("******************************************************");
					System.out.println("******************************************************");
					System.out.println("******************************************************");
					System.out.println("******************************************************");
					System.out.println("New High Score: " + goal +" set by "+ activePlayer.getName());
					System.out.println("******************************************************");
					System.out.println("******************************************************");
					System.out.println("******************************************************");
					System.out.println("******************************************************");
					System.out.println("******************************************************");
					System.out.println("------------------------------------------------------");			
					this.setLastRound();
					i = players.length; 
					System.out.println("STARTING THE LAST ROUND");
				}				
			}			
		}
			
 		for (int i = 1; i < players.length; i++) {
			startTurn();

			if(activePlayer.getGamePoints() >= goal){
				goal =(activePlayer.getGamePoints());
				System.out.println("******************************************************");
				System.out.println("******************************************************");
				System.out.println("******************************************************");
				System.out.println("******************************************************");
				System.out.println("******************************************************");
				System.out.println("New High Score: " + goal +" set by "+ activePlayer.getName());
				System.out.println("******************************************************");
				System.out.println("******************************************************");
				System.out.println("******************************************************");
				System.out.println("******************************************************");
				System.out.println("******************************************************");
				System.out.println("------------------------------------------------------");			
			}				
			
		}			

		//get the winner 
		int highScore   	= 0;
		int roundWinnerLoc 	= 0;
		
		for (int i = 0; i < players.length; i++) {
			if (players[i].getGamePoints()> highScore ) {
				highScore   = players[i].getGamePoints();
				roundWinnerLoc = i;
			}
		}
		System.out.println("The winner was "+ players[roundWinnerLoc].getName());
		System.out.println("Ask how winner wants to distribute the chips");
		
		
	}

	
	private String analyzeDiceValues() {

		String result = "";
		Player activePlayer = this.activePlayer;
		
		//roll (1 and 3-6) -> rolled 1 skunk and a 3 or a 4 or a 5 or a 6
		if ((activePlayer.getDie1RollValue()==1 && activePlayer.getDie2RollValue() > 2 ) 
		|| ( activePlayer.getDie1RollValue() >2 && activePlayer.getDie2RollValue()==1)){
	
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

	private void addChipsToKittyFromActivePlayer(int i) {
		activePlayer.takeNumberOfChips(i);
		addChipsToKitty(i);		
	}

	public void addChipsToKitty(int numberOfChipsToAddToKitty) {
		numberOfChipsInKitty = this.numberOfChipsInKitty+ numberOfChipsToAddToKitty;
	}	
	
	private void resetActivePlayersRoundPoints() {
		activePlayer.setRoundPoints(0);		
	}	
	
	private void resetActivePlayersGamePoints() {
		activePlayer.setGamePoints(0);;		
	} 
	
	private void updateTurnMetrics(String penalty){
		
		Player activePlayer   = this.activePlayer;
		String penaltyDetails = "#####################  PENALTY  ######################\n";	

		activePlayer.setTurnsTakenInCurrentRound(activePlayer.getTurnsTakenInCurrentRound()+1);		
		activePlayer.incrementTotalTurnsTaken();
		
		if(penalty.equalsIgnoreCase("OneSkunk")){

			activePlayer.setGamePoints(activePlayer.getGamePoints()-activePlayer.getRoundPoints());		
			this.resetActivePlayersRoundPoints();	
		 	this.addChipsToKittyFromActivePlayer(1);	
			
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Rolled One Skunk ::" + activePlayer.showRollDetails()+"\n");
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Lost: A turn, all round points &  1 chip (added to kitty)");
		 	penaltyDetails = penaltyDetails + ("\n######################################################");		
		 	
		 	penalty = "One Skunk";
		}
		
		else if(penalty.equalsIgnoreCase("TwoSkunks")){

			this.resetActivePlayersGamePoints();;			
		 	this.addChipsToKittyFromActivePlayer(4);
		 	activePlayer.setDoubleSkunkCount(activePlayer.getDoubleSkunkCount()+1);
		 	
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Rolled Two Skunks ::" + activePlayer.showRollDetails()+"\n");
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Lost: A turn, all game points &  4 chips (added to kitty)");
		 	penaltyDetails = penaltyDetails + ("\n######################################################");		

		 	penalty = "Two Skunks";

		}
		
		else if(penalty.equalsIgnoreCase("SkunkAndDeuce")){
			
			activePlayer.setGamePoints(activePlayer.getGamePoints()-activePlayer.getRoundPoints());	
			this.resetActivePlayersRoundPoints();			
		 	this.addChipsToKittyFromActivePlayer(2);	
 		 	
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Rolled One Skunk and a Deuce ::" + activePlayer.showRollDetails()+"\n");
		 	penaltyDetails = penaltyDetails + (activePlayer.getName()+ " Lost: A turn, all round points &  2 chips (added to kitty)");
		 	penaltyDetails = penaltyDetails + ("\n######################################################");		

		 	penalty = "One Skunk and a Deuce";

		}
		
		else{
			
			activePlayer.setTurnPoints(activePlayer.getRollValue());
			activePlayer.setRoundPoints(activePlayer.getRoundPoints()+activePlayer.getRollValue());	
			activePlayer.setGamePoints(activePlayer.getGamePoints()+activePlayer.getRollValue());
		}
		
		if (penalty.trim().length()>0) {
			System.out.println(penaltyDetails);	
		}

	}

	public void setLastRound() {		

		int cap			 			= players.length;			
		Player[] lastRoundSequence 	= new Player[cap];
		int playerLoc 	 			= getLoc(activePlayer);
		
		for (int i = 0; i < cap; i++) {
			lastRoundSequence[i] 	= players[playerLoc%cap];
			playerLoc++;
		}
		this.lastRoundSequence 		= lastRoundSequence;
		this.players=lastRoundSequence;
		this.isLastRound 			= true;	
		
		for (int i = 0; i < players.length; i++) {
			System.out.println("LR "+ i + " "+ players[i].getName());
		}
		activePlayerLoc = 1;

	}

	
	private int getLoc(Player player){	
		
		int cap = players.length;
		int loc = 0;		
		for (int i = 0; i < cap; i++) {
			if (players[i].equals(player)) {
				loc = i;
			}
		}
		return loc;
	}
	
	
	
	

}
