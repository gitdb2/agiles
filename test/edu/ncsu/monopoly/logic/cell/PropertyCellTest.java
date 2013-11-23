package edu.ncsu.monopoly.logic.cell;

import junit.framework.TestCase;
import edu.ncsu.monopoly.gui.mocks.MockGUI;
import edu.ncsu.monopoly.logic.GameMaster;
import edu.ncsu.monopoly.logic.gameboard.mocks.GameBoardSimple;

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
	
	public void testPlayerMonopollyRent() {
		PropertyCell cell 	= (PropertyCell) gameMaster.getGameBoard().queryCell("Green 1");
		int cellIndex		= gameMaster.getGameBoard().queryCellIndex("Green 1");
		
		gameMaster.movePlayer(0, cellIndex);
		gameMaster.getPlayer(0).purchase();//compra green 1
				
		gameMaster.movePlayer(0, 1);//mueve a green 2
		gameMaster.getPlayer(0).purchase();//compra green 2
		gameMaster.getPlayer(0).purchaseHouse("green", 1);
		
		
		gameMaster.switchTurn();
		
		cell 	= (PropertyCell) gameMaster.getGameBoard().queryCell("Green 2");
		cellIndex		= gameMaster.getGameBoard().queryCellIndex("Green 2"); //20 de renta
		gameMaster.movePlayer(1, cellIndex);

		
		int dosHousesBuy = 2* 70;
		int comprado = (1500 -200 -240) -dosHousesBuy;
		int numHouses = 1;
		int renta =  (20 * 2)*  (numHouses + 1);//2 por monopolio
		
		assertEquals(comprado , gameMaster.getPlayer(0).getMoney());
		assertEquals( (1500), gameMaster.getPlayer(1).getMoney());
			
		cell.playAction();
		
		
		assertEquals(renta, cell.getRent(Cell.NoInflationParameter));
		
		
		assertEquals( comprado + renta, gameMaster.getPlayer(0).getMoney());
		
		assertEquals( 1500 - renta, gameMaster.getPlayer(1).getMoney());
		
	
		
	}
	
	
}
