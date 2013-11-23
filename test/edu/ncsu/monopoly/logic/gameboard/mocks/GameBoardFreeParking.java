
package edu.ncsu.monopoly.logic.gameboard.mocks;

import edu.ncsu.monopoly.logic.cell.FreeParkingCell;
import edu.ncsu.monopoly.logic.cell.GoToJailCell;
import edu.ncsu.monopoly.logic.cell.JailCell;
import edu.ncsu.monopoly.logic.gameboarad.GameBoard;

public class GameBoardFreeParking extends GameBoard {
	public GameBoardFreeParking() {
		super();
		JailCell jail = new JailCell();
		FreeParkingCell freeParking = new FreeParkingCell();
		GoToJailCell goToJail = new GoToJailCell();
		addCell(jail);
		addCell(freeParking);
		addCell(goToJail);

	}
}
