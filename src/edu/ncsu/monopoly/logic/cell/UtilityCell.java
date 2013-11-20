package edu.ncsu.monopoly.logic.cell;

import edu.ncsu.monopoly.logic.GameMaster;
import edu.ncsu.monopoly.logic.Player;
import edu.ncsu.monopoly.logic.cell.visitor.property.collector.IVisitorCollectCells;

public class UtilityCell extends BuyableCell {

	private static final String COLOR_GROUP = "UTILITY";
	private static int PRICE;

	public UtilityCell() {
		super(COLOR_GROUP);
	}

	public static void setPrice(int price) {
		UtilityCell.PRICE = price;
	}

	public int getPrice() {
		return UtilityCell.PRICE;
	}

	public int getRent(int diceRoll) {
		int count = owner.getPropertyNumberForColor(COLOR_GROUP);
		if(count == 1) {
			return diceRoll * 4;
		} else if (count >= 2) {
			return diceRoll * 10;
		}
		return 0;
	}

	public void playAction() {
		Player currentPlayer = null;
		if(!isAvailable()) {
			currentPlayer = GameMaster.instance().getCurrentPlayer();
			if(owner != currentPlayer) {
				GameMaster.instance().utilRollDice();
				int diceRoll = GameMaster.instance().getUtilDiceRoll();
				currentPlayer.payRentTo(owner, getRent(diceRoll));
			}
		}
	}
	
	
	
	@Override
	public void acceptVisitorCollectCells(IVisitorCollectCells visitor) {
		 visitor.visit(this);
	}
}
