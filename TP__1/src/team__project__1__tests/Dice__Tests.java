/**
 * 
 */
package team__project__1__tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import team__project__1.Dice;
import team__project__1.Die;

/**
 * @author Hughbert Kumwesiga | Wasswa Derric
 * Tests for the Die Object. 
 * The Test tests both the Random Die and the Predictable Die
 */
public class Dice__Tests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//System.out.println("Testing the dice");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception{
		//System.out.println("No set Up required");
	}

	/**
	 * @Test random Die
	 */
	@Test
	public void randomDiceTest() {		
		System.out.println("Testing 2 random Die");
		Dice dice = new Dice();
		dice.roll();
		int die1RollValue = dice.getDie1RollValue();
		int die2RollValue = dice.getDie2RollValue();
		
		boolean range = (die1RollValue > 0 && die1RollValue < 7);		
		assertTrue(range);
		range = (die2RollValue > 0 && die2RollValue < 7);		
		assertTrue(range);
	}

	/**
	 * @Test run multiple tests for the random die
	 */
	@Test
	public void multipleRandomDiceTests(){		
		System.out.println("Testing a dice with  2 random Die 1000 times");		
	
		Dice dice = new Dice();
		int die1RollValue 	= 0;
		int die2RollValue 	= 0;		
		boolean range		= false; 		

		for (int i = 0; i < 1000; i++) {
			dice.roll();
			die1RollValue = dice.getDie1RollValue();
			die2RollValue = dice.getDie2RollValue();
			
			range = (die1RollValue > 0 && die1RollValue < 7);		
			assertTrue(range);
			range = (die2RollValue > 0 && die2RollValue < 7);		
			assertTrue(range);		
		}
	}
	
	/**
	 * @Test the predictable Die
	 */
	@Test
	public void predictableDiceTest() {		
		System.out.println("Testing Dice with 2 predictable Die");
		int[] die1Values = new int[]{1,2,5,4,6,3,2,5,1,4,5,2,3,6};
		int[] die2Values = new int[]{1,4,3,2,1,2,2,1,6,3,5,2,3,2};
		Dice dice = new Dice(new Die(die1Values),new Die(die2Values));
		
		dice.roll();
		assertEquals(1, dice.getDie1RollValue());
		assertEquals(1, dice.getDie2RollValue());
	}

	/**
	 * @Test run multiple tests for the predictable die
	 */
	@Test
	public void multiplePredictableDiceTest(){		
		System.out.println("Testing a dice with  2 predictable Die 1000 times");		
	
		int[] die1Values = new int[]{1,2,5,4,6,3,2,5,1,4,5,2,3,6};
		int[] die2Values = new int[]{1,4,3,2,1,2,2,1,6,3,5,2,3,2};
		Dice dice = new Dice(new Die(die1Values),new Die(die2Values));		

		for (int i = 0; i < 1000; i++) {
			dice.roll();
			assertEquals(die1Values[i%die1Values.length], dice.getDie1RollValue());
			assertEquals(die2Values[i%die2Values.length], dice.getDie2RollValue());
		}
	}

	/**
	 * @Test the one predictable Die and one random Die
	 */
	@Test
	public void diceWithPredictableDieRandomDieTest() {		
		System.out.println("Testing Dice with 1 predictable Die and one Random Die");
		int[] die1Values = new int[]{1,2,5,4,6,3,2,5,1,4,5,2,3,6};
		Dice dice = new Dice(new Die(die1Values),new Die());
		
		int die2RollValue 	= 0;		
		boolean range		= false; 
		
		dice.roll();
		die2RollValue 	= dice.getDie2RollValue();
		range 			= (die2RollValue > 0 && die2RollValue < 7);		
		assertTrue(range);	
		assertEquals(1, dice.getDie1RollValue());
	}
 

	/**
	 * @Test multiple the one predictable Die and one random Die
	 */
	@Test
	public void multipleDiceWithPredictableDieRandomDieTest() {		
		System.out.println("Testing Dice with 1 predictable Die and one Random Die 1000*");
		int[] die1Values = new int[]{1,2,5,4,6,3,2,5,1,4,5,2,3,6};
		Dice dice = new Dice(new Die(die1Values),new Die());
		
		int die2RollValue 	= 0;		
		boolean range		= false; 
		
		for (int i = 0; i < 1000; i++) {
			dice.roll();
			die2RollValue 	= dice.getDie2RollValue();
			range 			= (die2RollValue > 0 && die2RollValue < 7);		
			assertTrue(range);	
			assertEquals(die1Values[i%die1Values.length], dice.getDie1RollValue());			
		}

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("completed test case");
	}	
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("completed test suite");
	}	
	
}
