package edu.ncsu.monopoly.logic.gameboard.mocks;

import edu.ncsu.monopoly.logic.card.Card;
import edu.ncsu.monopoly.logic.card.MovePlayerCard;
import edu.ncsu.monopoly.logic.cell.CardCell;
import edu.ncsu.monopoly.logic.cell.JailCell;
import edu.ncsu.monopoly.logic.cell.PropertyCell;
import edu.ncsu.monopoly.logic.gameboarad.GameBoard;


public class GameBoardCCMovePlayer extends GameBoard {
    public GameBoardCCMovePlayer() {
		super();
		PropertyCell blue1 = new PropertyCell();
		PropertyCell blue2 = new PropertyCell();
        CardCell cc1 = new CardCell(Card.TYPE_CC, "Community Chest 1");
        JailCell jail = new JailCell();
        CardCell chance1 = new CardCell(Card.TYPE_CHANCE, "Chance 1");
        
        PropertyCell red1 = new PropertyCell();
        
        
        Card ccCard1 = new MovePlayerCard("Blue 1" , Card.TYPE_CC);
        Card ccCard2 = new MovePlayerCard("Blue 2", Card.TYPE_CC);
        Card chanceCard1 = new MovePlayerCard("Blue 1", Card.TYPE_CHANCE);
        
        Card chanceCard2 = new MovePlayerCard("Red 1", Card.TYPE_CHANCE);
		
		blue1.setName("Blue 1");
		blue2.setName("Blue 2");
		red1.setName("Red 1");

		blue1.setColorGroup("blue");
		blue2.setColorGroup("blue");
		red1.setColorGroup("red");
		
		blue1.setPrice(100);
		blue2.setPrice(100);
		red1.setPrice(100);
		
		blue1.setRent(10);
		blue2.setRent(10);
		red1.setRent(10);
		
		blue1.setHousePrice(50);
		blue2.setHousePrice(50);
		red1.setHousePrice(50);
		
		
		addCard(ccCard1);
		addCard(ccCard2);
		
		addCard(chanceCard1);
		addCard(chanceCard2);//rojo
		
		
		addCell(blue1);
		addCell(cc1);
		addCell(jail);
		addCell(blue2);
		addCell(chance1);
		addCell(red1);
		
    }
}
