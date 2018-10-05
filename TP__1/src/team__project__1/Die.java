package team__project__1;


public class Die{
	
	private int rollValue;
	private int rollPosition;
	/* @Param rollValues::(For Testable Die)
	  We will put the testable die values here so that this is controlled by the Die Class
	  We can change this (remove final) and give it a getter and setter if we would like to 
	  access or modify it from another class with the test engineers own values
	*/
	private int[] rollValues;
	private boolean isTestableDie;
	
	private int lastRoll;
	private boolean predictable = false;
	private int[] rolls;
	private int index_of_last_roll = 0;
		
	
	
	public Die()
	{
		this.roll();
	}
	/*
	 This Overloaded constructor will build a predictable die based on predetermined roll (values)
	 The random nature of rolling dice makes it tricky to test your evolving SkunkApp. 
	 Thus, start by modifying Die to allow it to be initialized with
	  a sequence of "pre-programmed" die values returned 
	 */
	public Die(int[] rollValues){
		this.rollValues = rollValues;
		this.rollPosition=0;
		this.isTestableDie = true;
		
		if(rollValues== null) 
		{
			throw new RuntimeException("null initializing int[] array ");
		}
		this.predictable = true;
		this.rolls = rollValues; 
	}	
	
	
	
	// getter or accessor method
	public int getRollValue(){
		return this.rollValue;
	}
	
	// setter method
	public void setRollValue(int rollValue){
		this.rollValue = rollValue;
	}
	
	// note how this changes Die's state, but doesn't return anything
	public void roll(){
		/*
		 The random nature of rolling dice makes it tricky to test your evolving SkunkApp. 
		 Thus, start by modifying Die to allow it to be initialized with
		  a sequence of "pre-programmed" die values returned 
		 */		
		if(this.isTestableDie){
			int rollValue = rollValues[this.rollPosition%this.rollValues.length];
			setRollValue(rollValue);		
			setRollPosition(rollPosition+1);				
		}
		else{
			int rollValue = (int) (Math.random() * 6 + 1);
			setRollValue(rollValue);				
		}	
	}
	
	private void setRollPosition(int i) {
		this.rollPosition = i;		
	}

	// this OVERRIDES the default Object.toString()
	@Override
	public String toString(){
		return "Die: " + this.getRollValue();
	}

}


