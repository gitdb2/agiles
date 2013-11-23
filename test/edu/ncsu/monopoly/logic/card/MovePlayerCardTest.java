package edu.ncsu.monopoly.logic.card;



import edu.ncsu.monopoly.gui.mocks.MockGUI;
import edu.ncsu.monopoly.logic.GameMaster;
import edu.ncsu.monopoly.logic.card.Card;
import edu.ncsu.monopoly.logic.card.MovePlayerCard;
import edu.ncsu.monopoly.logic.cell.Cell;
import edu.ncsu.monopoly.logic.gameboard.mocks.GameBoardCCMovePlayer;
import junit.framework.TestCase;

public class MovePlayerCardTest extends TestCase {
    GameMaster gameMaster;
    Card movePlayerCard;
    
    protected void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardCCMovePlayer());
		gameMaster.setNumberOfPlayers(1);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
		movePlayerCard = new MovePlayerCard("Blue 1", Card.TYPE_CC);
		gameMaster.getGameBoard().addCard(movePlayerCard);
    }
    
    public void testJailCardLabel() {
        assertEquals("Go to Blue 1", movePlayerCard.getLabel());
    }
    
   
    public void testMovePlayerCardUI() {
        gameMaster.movePlayer(0, 2);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        
        gameMaster.btnDrawCardClicked();
        
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
		Cell cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
		assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
		assertEquals(1700, gameMaster.getCurrentPlayer().getMoney());
    }
}
