package team__project__1;

public class Die{
	private int rollValue;
	private int rollPosition;
	private final int[] rollValues = new int[]{1,2,3,4,5,6,5,4,3,2,1};
	private boolean isTestableDie;
		
	public Die(){}
	
	public Die(boolean isTestable){
		this.rollPosition=0;
		this.isTestableDie = isTestable;
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


