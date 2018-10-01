package team__project__1;


public class Player extends Dice{

	private String name;
	private int numberOfChips;
	private int turnPoints;
	private int roundPoints;
	private int gamePoints; 
	private int totalTurnsTaken;
	private int turnsTakenInCurrentRound;	
	private int playerNumber;
	private int doublSkunkCount;
	
	private int previousNumberOfChips;
	private int previousTurnPoints;
	private int previousRoundPoints;
	private int previousGamePoints; 
	private int previousDoublSkunkCount;
 
	
	public Player(String name) {
		
		super();
		this.name 						= name;
		this.numberOfChips 				= 50;
		this.turnPoints 				= 0;
		this.roundPoints 				= 0;
		this.gamePoints 				= 0; 
		this.totalTurnsTaken			= 0; 
		this.turnsTakenInCurrentRound 	= 0;	
		this.doublSkunkCount 			= 0;
		
		this.previousNumberOfChips 		= this.numberOfChips;
		this.previousTurnPoints 		= 0;
		this.previousRoundPoints 		= 0;
		this.previousGamePoints 		= 0; 
		this.previousDoublSkunkCount 	= 0;
	} 	
	
	@Override
	public void roll(){		
		super.roll();
	}

	public int getPlayerNumber()					{return this.playerNumber;				}
	public void setPlayerNumber(int playerNumber) 	{this.playerNumber = playerNumber;		}	
	public void setNumberOfChips(int numberOfChips) {this.numberOfChips = numberOfChips;	} 		
	public int getNumberOfChips() 					{return this.numberOfChips;				}	
	public String getName() 						{return this.name;						}			
	public int getTurnPoints() 						{return turnPoints;						}	
	public void setTurnPoints(int turnPoints) 		{this.turnPoints = turnPoints;			}	
	public int getRoundPoints() 					{return this.roundPoints;				}	
	public void setRoundPoints(int roundPoints) 	{this.roundPoints = roundPoints;		}	
	public int getGamePoints() 						{return this.gamePoints;				}	
	public int getDoubleSkunkCount() 				{return this.doublSkunkCount;			}
	public void setDoubleSkunkCount(int i) 			{this.doublSkunkCount = i;				}
	public int getTotalTurnsTaken() 				{return this.totalTurnsTaken;			}	
	public void incrementTotalTurnsTaken() 			{this.totalTurnsTaken++;				}	
	public int getTurnsTakenInCurrentRound() 		{return this.turnsTakenInCurrentRound;	}

	
	public void setGamePoints(int gamePoints) {
		if (gamePoints <0) {
			this.gamePoints =0;
		}
		else{
			this.gamePoints = gamePoints;	
		}		
	}
	
	public void setTurnsTakenInCurrentRound(int turnsTakenInCurrentRound) {
		this.turnsTakenInCurrentRound = turnsTakenInCurrentRound;
	}

	public void takeNumberOfChips(int i) {

		if (i < this.getNumberOfChips()) {
			this.setNumberOfChips(this.getNumberOfChips()-i);	
		}
		else{
			System.out.println(this.getName() + " Doesn't have sufficient chips");
			this.setNumberOfChips(this.getNumberOfChips()-i);	
		}			
	}
	
	public void setPreviousMetrics(){
		
		this.previousNumberOfChips 		=this.getNumberOfChips();
		this.previousTurnPoints 		=this.getTurnPoints();
		this.previousRoundPoints 		=this.getRoundPoints();
		this.previousGamePoints 		=this.getGamePoints(); 
		this.previousDoublSkunkCount 	=this.getDoubleSkunkCount();		
	}
	
	@Override
	public String toString() {
	
		String result = "";
		result = result + "Name \t\t\t\t\t: " 							 +this.getName()						+ "\n";
		result = result + "Player Number \t\t\t\t: " 					 +"Player "+this.getPlayerNumber()		+ "\n";
		result = result + "Number of Turns taken in this Round\t: " 	 +this.getTurnsTakenInCurrentRound()	+ "\n";
		result = result + "Total turns taken in all rounds\t\t: " 		 +this.getTotalTurnsTaken()				+ "\n";;
		result = result + "Number of Chips \t\t\t: " 					 +this.previousNumberOfChips			+ "\t=> "+this.getNumberOfChips()	+"\n";
		result = result + "Total Game Points\t\t\t: " 					 +this.previousGamePoints 				+ "\t=> "+this.getGamePoints()		+"\n";
		result = result + this.getName() +"'s Current Round Points\t\t: "+this.previousRoundPoints 				+ "\t=> "+this.getRoundPoints()		+"\n";
		result = result + this.getName() +"'s Double Skunk Count  \t\t: "+this.previousDoublSkunkCount 			+ "\t=> "+this.getDoubleSkunkCount()	+"\n";
		result = result + "Current Points accumulated in this Turn\t: "  +this.previousTurnPoints 				+ "\t=> "+this.getTurnPoints()		+"\n";

		setPreviousMetrics();
		return result;
	}

}
