package edu.ncsu.monopoly.logic.die;

public class DeterministicDie extends Die {
	int [] diethrows = new int[]{2,3,3,4,5,6,1};
	int index = 0;
	public int getRoll() {
		return diethrows[index++ % diethrows.length];
	}
}
