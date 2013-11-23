package edu.ncsu.monopoly.logic.cell;

public class GoCell extends NotBuyableCell {
	public GoCell() {
		super.setName("Go");
		setAvailable(false);
	}

	@Override
	public void playAction() {
		// Do NOTHING
		
	}

}
