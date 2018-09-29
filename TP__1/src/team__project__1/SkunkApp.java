package team__project__1;

import java.io.*;

public class SkunkApp {

	public static void main(String[] args) {

 
//		The Game is initialized: 
//			User input is obtained initializing numPlayers and Players Array.
//			Players are assigned numbers
//			Game starts a round
//				player n rolls Dice:
//				Game analyzes Dice For Penalties		
//				Game updates Game Metrics
//				Game updates Player Metrics		
//				Game presents options : if current player was penelized, next player's turn: else: ask to play again
		
//		Game observes the high score and checks if it has been beat. 
//			1. if the highScore is beat, game sets the next round as the last Round excluding the player who just beat the high score
//			2. if the highScore is beat, game starts the final round from the next player. 
//		
//		Game updates the round in all players and resets round points and turns etc
 	
		
		//while currentUserCanContinue
			//ask if they want to continue
				//if yes play another Turn. 
				
		
		
		Game game 				= new Game();
		Player activePlayer 	= null;
		BufferedReader reader 	= null;
		String penalty 			= "";
		String input 			= "";
 		
		while(!game.isLastRound()){
			
			activePlayer = game.getActivePlayer();
			
			System.out.println("\nROUND " +game.getRoundNumber()+" :: "+activePlayer.getName()+" BEFORE THE ROLL");	
			System.out.println("------------------------------------------------------");
			System.out.println("NUMBER OF CHIPS IN THE GAME'S KITTY \t: " + game.getNumberOfChipsInKitty());
			System.out.println("------------------------------------------------------");

			System.out.println(activePlayer);
			
			if(activePlayer.getTurnsTakenInCurrentRound() == 0) {
				
 				activePlayer.roll();
				System.out.println("IT IS " +activePlayer.getName()+"'s TURN \n"  +activePlayer.getName()+ " Rolled :: Roll value: "+ game.getRollValue() + " =>"+"D1 value: "  + game.getDie1RollValue() +" & "+ "D2 value: "  + game.getDie2RollValue());	

				
				penalty = game.analyzeDiceValues();
				game.updatePlayerMetrics(penalty);

				System.out.println("\nROUND " +game.getRoundNumber()+" :: TURN: "+ (activePlayer.getTurnsTakenInCurrentRound()) +activePlayer.getName()+"'s RESULTS  AFTER THE ROLL");	
				System.out.println("------------------------------------------------------");
				System.out.println("NUMBER OF CHIPS IN THE GAME'S KITTY \t: " + game.getNumberOfChipsInKitty());
				System.out.println("------------------------------------------------------");

				System.out.println(activePlayer);					
				
			}
			else{
				//ask player if they would like to roll again				
				System.out.println(activePlayer.getName()+ ", Would you like to roll again? ->Enter Y/N");
				
				try {
			
					reader 	= new BufferedReader(new InputStreamReader(System.in));
					input 	= reader.readLine();
					
					if(input.equalsIgnoreCase("Y")){
						System.out.println("------------------------------------------------------");

						System.out.println("\nROUND " +game.getRoundNumber()+" :: "+activePlayer.getName()+" BEFORE THE ROLL");	
						System.out.println("------------------------------------------------------");
						System.out.println("NUMBER OF CHIPS IN THE GAME'S KITTY \t: " + game.getNumberOfChipsInKitty());
						System.out.println("------------------------------------------------------");

						System.out.println(activePlayer);
						
						activePlayer.roll();
						System.out.println("IT IS " +activePlayer.getName()+"'s TURN \n"  +activePlayer.getName()+ " Rolled :: Roll value: "+ game.getRollValue() + " =>"+"D1 value: "  + game.getDie1RollValue() +" & "+ "D2 value: "  + game.getDie2RollValue());	

						
						penalty = game.analyzeDiceValues();
						game.updatePlayerMetrics(penalty);
						System.out.println("------------------------------------------------------");

						System.out.println("\nROUND " +game.getRoundNumber()+" :: "+ (activePlayer.getTurnsTakenInCurrentRound()-1) +"RESULTS"+activePlayer.getName()+" AFTER THE ROLL");	
						System.out.println("------------------------------------------------------");
						System.out.println("NUMBER OF CHIPS IN THE GAME'S KITTY \t: " + game.getNumberOfChipsInKitty());
						System.out.println("------------------------------------------------------");

						System.out.println(activePlayer);					
 					}
					else{
						
						game.setActivePlayerToNextPlayer();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				//get the highest score
				
			}

		}
		
		
		
		//This is the last round
		System.out.println("This is the last round");		
		Player[] p = game.getLastRoundSequence();
		
		
		for (int i = 1; i < p.length; i++) {
			System.out.println("Player " +i+ " " +  p[i].getName() + " but is actually Player "+ p[i].getPlayerNumber());

			
			while(!activePlayer.equals(p[0])) {
				activePlayer = game.getActivePlayer();				
				System.out.println("Last Round active player");
				System.out.println(activePlayer);
				
				if(activePlayer.getTurnsTakenInCurrentRound() == 0) {
					
 					activePlayer.roll();
					System.out.println("IT IS " +activePlayer.getName()+"'s TURN \n"  +activePlayer.getName()+ " Rolled :: Roll value: "+ game.getRollValue() + " =>"+"D1 value: "  + game.getDie1RollValue() +" & "+ "D2 value: "  + game.getDie2RollValue());	

					
					penalty = game.analyzeDiceValues();
					game.updatePlayerMetrics(penalty);

					System.out.println("------------------------------------------------------");
					System.out.println("ROUND NUMBER " +game.getRoundNumber()+" :: AFTER THE ROLL");	
					System.out.println("------------------------------------------------------");
					System.out.print ("NUMBER OF CHIPS IN THE GAME'S KITTY \t: " + game.getNumberOfChipsInKitty());
					System.out.println("------------------------------------------------------");

					System.out.println(activePlayer);						
				}
				else{
					//ask player if they would like to roll again				
					System.out.println("Would you like to roll again? ->Enter Y/N");
					
					try {
				
						reader 	= new BufferedReader(new InputStreamReader(System.in));
						input 	= reader.readLine();
						
						if(input.equalsIgnoreCase("Y")){
							System.out.println("------------------------------------------------------");
							System.out.println("\nROUND NUMBER " +game.getRoundNumber()+" :: BEFORE THE ROLL");	
							System.out.println("------------------------------------------------------");
							System.out.println("NUMBER OF CHIPS IN THE GAME'S KITTY \t: " + game.getNumberOfChipsInKitty());
							System.out.println("------------------------------------------------------");

							System.out.println(activePlayer);
							
 							activePlayer.roll();
							System.out.println("IT IS " +activePlayer.getName()+"'s TURN \n"  +activePlayer.getName()+ " Rolled :: Roll value: "+ game.getRollValue() + " =>"+"D1 value: "  + game.getDie1RollValue() +" & "+ "D2 value: "  + game.getDie2RollValue());	

							
							penalty = game.analyzeDiceValues();
							game.updatePlayerMetrics(penalty);

							System.out.println("------------------------------------------------------");
							System.out.println("ROUND NUMBER " +game.getRoundNumber()+" :: AFTER THE ROLL");	
							System.out.println("------------------------------------------------------");
							System.out.println("NUMBER OF CHIPS IN THE GAME'S KITTY \t: " + game.getNumberOfChipsInKitty());
							System.out.println("------------------------------------------------------");

							System.out.println(activePlayer);					
							input = "";
						}
						else{
							
							game.setActivePlayerToNextPlayer();
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}				
			}		
		}
		
		
		//get the winner
		//distribute chips
		
		System.out.println("Thanks for playing Skunk :) GAME OVER");
		
		
 
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


			

	
 
		
 
		

	}

}
