package edu.ncsu.monopoly.logic.cell;

import edu.ncsu.monopoly.logic.GameMaster;
import edu.ncsu.monopoly.logic.Player;

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
		if(owner.numberOfUtil() == 1) {
			return diceRoll * 4;
		} else if (owner.numberOfUtil() >= 2) {
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
}
