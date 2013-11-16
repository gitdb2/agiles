package edu.ncsu.monopoly.logic.card;



import edu.ncsu.monopoly.gui.mocks.MockGUI;
import edu.ncsu.monopoly.logic.GameMaster;
import edu.ncsu.monopoly.logic.card.Card;
import edu.ncsu.monopoly.logic.card.MoneyCard;
import edu.ncsu.monopoly.logic.gameboarad.GameBoardCCGainMoney;
import junit.framework.TestCase;

public class CardsTest extends TestCase {
    Card ccCard, chanceCard;
    
    GameMaster gameMaster;

    protected void setUp() {
        gameMaster = GameMaster.instance();
        gameMaster.setGameBoard(new GameBoardCCGainMoney());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        ccCard = new MoneyCard("Get 50 dollars", 50, Card.TYPE_CC);
        chanceCard = new MoneyCard("Lose 50 dollars", -50, Card.TYPE_CHANCE);
        gameMaster.getGameBoard().addCard(ccCard);
    }
    
    public void testCardType() {
        Card card = gameMaster.drawCCCard();
        assertEquals(Card.TYPE_CC, ccCard.getCardType());
        card = gameMaster.drawChanceCard();
        assertEquals(Card.TYPE_CHANCE, chanceCard.getCardType());
    }
}
