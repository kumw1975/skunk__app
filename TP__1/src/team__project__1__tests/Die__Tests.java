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

import team__project__1.Die;

/**
 * @author Hughbert Kumwesiga | Wasswa Derric
 * Tests for the Die Object. 
 * The Test tests both the Random Die and the Predictable Die
 */
public class Die__Tests {
	private static Die randomDie;
	private static int randomRollValue;
	private static int predictableRollValue;
	private static Die predictableDie;
	private static boolean range;
	private static int[] predictableRollValues;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("\ninstantiated a pridictable die and a random die");
		Die__Tests.randomDie 			= new Die();
		Die__Tests.predictableRollValues = new int[]{1,2,6,4,4,1,2,3,4,5,2,3};
		Die__Tests.predictableDie 		= new Die(Die__Tests.predictableRollValues);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception{
		System.out.println("\nRunning Test setUp");
		Die__Tests.randomDie.roll();
		Die__Tests.predictableDie.roll();
		Die__Tests.randomRollValue 		= Die__Tests.randomDie.getRollValue();
		Die__Tests.predictableRollValue 	= Die__Tests.predictableDie.getRollValue();
		Die__Tests.range = (randomRollValue > 0 && randomRollValue < 7);	
	}

	/**
	 * @Test random Die
	 */
	@Test
	public void randomDieTest() {		
		System.out.println("\nTesting random Die");
		assertTrue(Die__Tests.range);
	}

	/**
	 * @Test run multiple tests for the random die
	 */
	@Test
	public void multipleRandomDieTests(){		
		System.out.println("\nTesting random Die 1000 times");
		for (int i = 0; i < 1000; i++) {
			Die__Tests.randomDie.roll();
			Die__Tests.randomRollValue 	= Die__Tests.randomDie.getRollValue();
			Die__Tests.range = (Die__Tests.randomRollValue > 0 && Die__Tests.randomRollValue < 7);		
			assertTrue(Die__Tests.range);			
		}
	}
	
	/**
	 * @Test the predictable Die
	 */
	@Test
	public void predictableDie() {
		System.out.println("\nTesting predictable Die");
		assertEquals(1, Die__Tests.predictableDie.getRollValue());
	}
	
	@Test
	public void multiplePredictableDieTests(){	
		System.out.println("\nTesting predictable Die 1000 times");
		Die__Tests.predictableDie 		= new Die(Die__Tests.predictableRollValues);
		
		for (int i = 0; i < 1000; i++) {
			Die__Tests.predictableDie.roll();
			assertEquals(predictableRollValues[(i)%predictableRollValues.length], Die__Tests.predictableDie.getRollValue());
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("\ncompleted test case");
	}	
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("\ncompleted test suite");
	}	
	
}
