package team__project__1__tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import team__project__1.Die;

public class Predictable_die_test {
	
	private Die die;

	@Before
	public void setUp() throws Exception 
	{
		int[] int_values = new int[] {3,2,1};
		die = new Die(int_values);
	}

	@After
	public void tearDown() throws Exception 
	{
		
	}

	@Test
	public void test_initialization_0f_predictable_die() 
	{
		die.roll();
		int value = die.getRollValue();
		assertEquals("assert not equals 3",3,value);
	}
	
	@Test
	public void test_roll_2_of_predictable_die()
	{
		die.roll();
		assertEquals("first not equals 3",3,die.getRollValue());
		die.roll();
		assertEquals("second not equals 2",2,die.getRollValue());
			
		
	}
	
	@Test
	public void test_roll_3_of_predictable_die()
	{
		
		die.roll();
		die.roll();
		die.roll();
		assertEquals("third not equals 1",1,die.getRollValue());
			
		
	}
	
	@Test
	public void test_roll_4_of_predictable_die_with_3_rolls()
	{
		
		die.roll();
		die.roll();
		die.roll();
		die.roll();
		assertEquals("fourth value not wrapping back to first value 3",3,die.getRollValue());
			
		
	}
	
	@Test
	public void test_roll_5_of_predictable_die_with_3_rolls()
	{
		
		die.roll();
		die.roll();
		die.roll();
		die.roll();
		die.roll();
		assertEquals("fourth value not wrapping back to first value 3",2,die.getRollValue());
			
		
	}
	
	@Test(expected=RuntimeException.class)
	public void test_initial_int_array() 
	{
		Die die = new Die(null);
		die.roll();
	}

}

