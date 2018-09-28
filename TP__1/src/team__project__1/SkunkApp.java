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
 	
		Game game = new Game();
		Player activePlayer = game.getActivePlayer();

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
