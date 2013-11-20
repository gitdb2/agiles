package edu.ncsu.monopoly.logic.cell;

import edu.ncsu.monopoly.logic.GameMaster;
import edu.ncsu.monopoly.logic.Player;

public class GoToJailCell extends NotBuyableCell {
	
	public GoToJailCell() {
		setName("Go to Jail");
	}

	public void playAction() {
		Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		JailCell jail = (JailCell)(GameMaster.instance().getGameBoard().queryCell("Jail"));
		GameMaster.instance().sendToJail(currentPlayer);
	}
}
