package edu.ncsu.monopoly.logic.die;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DieTest {

	Die die;
	DeterministicDie die2;
	@Before
	public void setUp() throws Exception {
		die =  new Die();
		die2 = new DeterministicDie();
	}

	@Test
	public void testDieRange() {
		assertTrue(die.getRoll() <= 6);
		assertTrue(die.getRoll() >= 1);
	}
	
	@Test
	public void testDieDeterministicRange() {
		assertTrue(die2.getRoll() <= 6);
		assertTrue(die2.getRoll() >= 1);
	}

	
	@Test
	public void testDieDeterministicCount() {
		
		Integer[] arr = {0,0,0,0,0,0,0};
	
		for (int i = 0; i < 100; i++) {
			int dieVal =die2.getRoll();
			arr[dieVal] = arr[dieVal] +1;
		}
		
		int maxIdx = 0;
		int max = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] >= max){
				maxIdx = i;
				max = arr[i];
			}
		}
		
		assertEquals(maxIdx, 3);
	}

	@Test
	public void testDieDeterministicValues() {
		
		int [] diethrows = new int[]{2,3,3,4,5,6,1};
		for (int i = 0; i < diethrows.length; i++) {
			assertEquals(die2.getRoll(), diethrows[i]);
		}
		
	}
}
