package edu.ncsu.monopoly.logic.die;

public class Die {
	public int getRoll() {
		return (int)(Math.random() * 6) + 1;
	}
}
