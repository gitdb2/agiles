package edu.ncsu.monopoly.logic.cell;




import javax.naming.NoInitialContextException;

import edu.ncsu.monopoly.gui.mocks.MockGUI;
import edu.ncsu.monopoly.logic.GameMaster;
import edu.ncsu.monopoly.logic.cell.Cell;
import edu.ncsu.monopoly.logic.cell.PropertyCell;
import edu.ncsu.monopoly.logic.gameboard.mocks.GameBoardSimple;
import junit.framework.TestCase;

public class PropertyCellTest extends TestCase {

	GameMaster gameMaster;
	
	protected void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardSimple());
		gameMaster.setNumberOfPlayers(2);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
	}
	
	public void testPlayerAction() {
		PropertyCell cell =
			(PropertyCell) gameMaster.getGameBoard().queryCell("Blue 3");
		int cellIndex = gameMaster.getGameBoard().queryCellIndex("Blue 3");
		gameMaster.movePlayer(0, cellIndex);
		gameMaster.getPlayer(0).purchase();
		gameMaster.switchTurn();
		gameMaster.movePlayer(1, cellIndex);
		cell.playAction();
		assertEquals(
				1500 - cell.getRent(Cell.NoInflationParameter),
				gameMaster.getPlayer(1).getMoney());
		assertEquals(
				1380 + cell.getRent(Cell.NoInflationParameter),
				gameMaster.getPlayer(0).getMoney());
	}
}
