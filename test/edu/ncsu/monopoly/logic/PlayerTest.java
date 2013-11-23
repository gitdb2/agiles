package edu.ncsu.monopoly.logic;



import edu.ncsu.monopoly.gui.mocks.MockGUI;
import edu.ncsu.monopoly.logic.GameMaster;
import edu.ncsu.monopoly.logic.Player;
import edu.ncsu.monopoly.logic.cell.Cell;
import edu.ncsu.monopoly.logic.gameboarad.GameBoard;
import edu.ncsu.monopoly.logic.gameboard.mocks.GameBoardSimple;
import junit.framework.TestCase;

public class PlayerTest extends TestCase {

	GameMaster gameMaster;
	
	protected void setUp() throws Exception {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardSimple());
        gameMaster.setGUI(new MockGUI());
        gameMaster.setTestMode(true);
        gameMaster.reset();
	}
	
	public void testPurchaseProperty() {
		gameMaster.setNumberOfPlayers(1);
		gameMaster.movePlayer(0, 3);
		Player player = gameMaster.getPlayer(0);
		player.purchase();
		assertEquals(1380, player.getMoney());
		assertEquals("Blue 3", player.getProperty(0).getName());
		Cell cell =
			(Cell) gameMaster.getGameBoard().queryCell("Blue 3");
		assertSame(player, cell.getOwner());
	}

	public void testSameGoCell() {
		GameBoard gameboard = gameMaster.getGameBoard();
		Player player1 = new Player();
		Player player2 = new Player();
		Cell go = gameboard.queryCell("Go");
		assertSame(go, player1.getPosition());
		assertSame(go, player2.getPosition());
	}
	
	public void testPayRentTo() {
		gameMaster.setNumberOfPlayers(2);
		gameMaster.movePlayer(0,4);
		gameMaster.getCurrentPlayer().purchase();
		gameMaster.btnEndTurnClicked();
		gameMaster.movePlayer(1,4);
		gameMaster.btnEndTurnClicked();
		assertTrue(gameMaster.getPlayer(1).isBankrupt());
		assertEquals(2800, gameMaster.getPlayer(0).getMoney());
	}
	
	public void testExchangeProperty() {
		gameMaster.setNumberOfPlayers(2);
		gameMaster.movePlayer(0,3);
		gameMaster.getCurrentPlayer().purchase();
		gameMaster.btnEndTurnClicked();
		gameMaster.getPlayer(0).exchangeProperty(gameMaster.getPlayer(1));
		assertEquals(1,gameMaster.getCurrentPlayer().getPropertyNumber());
	}
	
	public void testPurchaseHouse() {
		gameMaster.setNumberOfPlayers(1);
		gameMaster.startGame();
		gameMaster.movePlayer(gameMaster.getCurrentPlayerIndex(),1);
		gameMaster.getCurrentPlayer().purchase();
		gameMaster.btnEndTurnClicked();
		gameMaster.movePlayer(0,1);
		gameMaster.getCurrentPlayer().purchase();
		gameMaster.btnEndTurnClicked();
		gameMaster.movePlayer(0,1);
		gameMaster.getCurrentPlayer().purchase();
		gameMaster.btnEndTurnClicked();
		gameMaster.getCurrentPlayer().purchaseHouse("blue",2);
		assertEquals("blue", gameMaster.getCurrentPlayer().getMonopolies()[0]);
		assertEquals(880, gameMaster.getCurrentPlayer().getMoney());
	}
	
	public void testResetProperty() {
		gameMaster.setNumberOfPlayers(1);
		gameMaster.movePlayer(0,1);
		gameMaster.getCurrentPlayer().purchase();
		assertEquals(gameMaster.getGameBoard().getCell(1), gameMaster.getCurrentPlayer().getAllProperties()[0]);
		gameMaster.getCurrentPlayer().resetProperty();
		assertEquals(0,gameMaster.getCurrentPlayer().getAllProperties().length);
	}
}
