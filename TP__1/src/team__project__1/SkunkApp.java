package team__project__1;

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
				
		
		
		Game game = new Game();
		Player activePlayer = game.getActivePlayer();
		
 		
		while(!game.isLastRound()){
			
			//Play has as many turns as they wish as long as they dont get a penalty;
			
			//roll the dice
			
			//analyze the dice
			
			//if no penalty -ask user if they want to roll again
				//if user says yes -> go to step 2
			
			//ask user if they wish to play again
		}
		
		
		while (game.getGoal() <= 100) {
			activePlayer = game.getActivePlayer();
			
			System.out.println("\nROUND NUMBER " +game.getRoundNumber()+" :: BEFORE THE ROLL");	
			System.out.println("------------------------------------------------------");
			System.out.println("NUMBER OF CHIPS IN THE GAME'S KITTY \t: " + game.getNumberOfChipsInKitty());
			System.out.println("------------------------------------------------------");

 
			System.out.println(activePlayer);
			
			activePlayer.roll();
			System.out.println("Roll value: "+ activePlayer.getRollValue());
			System.out.println("D1 value: "  + activePlayer.getDie1RollValue());
			System.out.println("D2 value: "  + activePlayer.getDie2RollValue());
			String penalty = game.analyzeDiceValues();
			game.updatePlayerMetrics(penalty);
	
 
			System.out.println("ROUND NUMBER " +game.getRoundNumber()+" :: AFTER THE ROLL");	
			System.out.println("------------------------------------------------------");
			System.out.println("NUMBER OF CHIPS IN THE GAME'S KITTY \t: " + game.getNumberOfChipsInKitty());
			System.out.println("------------------------------------------------------");

			System.out.println(activePlayer);			
		}
		

	}

}
