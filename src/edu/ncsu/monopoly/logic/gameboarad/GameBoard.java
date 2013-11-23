package edu.ncsu.monopoly.logic.gameboarad;

import java.util.ArrayList;
import java.util.Hashtable;

import edu.ncsu.monopoly.logic.card.Card;
import edu.ncsu.monopoly.logic.cell.BuyableCell;
import edu.ncsu.monopoly.logic.cell.Cell;
import edu.ncsu.monopoly.logic.cell.GoCell;
import edu.ncsu.monopoly.logic.cell.PropertyCell;

public abstract class GameBoard {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
    private ArrayList<Card> chanceCards = new ArrayList<Card>();
	//the key of colorGroups is the name of the color group.
	private Hashtable<String, Integer> colorGroups = new Hashtable<String, Integer>();
	private ArrayList<Card> communityChestCards = new ArrayList<Card>();

	
	public GameBoard() {
		addCell(new GoCell());
	}

    public void addCard(Card card) {
        if(card.getCardType() == Card.TYPE_CC) {
            communityChestCards.add(card);
        } else {
            chanceCards.add(card);
        }
    }
	
    
	public void addCell(Cell cell) {
		cells.add(cell);
	}
	
	public void addCell(BuyableCell cell) {
		int propertyNumber = getPropertyNumberForColor(cell.getColorGroup());
		colorGroups.put(cell.getColorGroup(), new Integer(propertyNumber + 1));
        cells.add(cell);
	}

    public Card drawCCCard() {
        Card card = communityChestCards.get(0);
        communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    public Card drawChanceCard() {
        Card card = chanceCards.get(0);
        chanceCards.remove(0);
        addCard(card);
        return card;
    }

	public Cell getCell(int newIndex) {
		return cells.get(newIndex);
	}
	
	public int getCellNumber() {
		return cells.size();
	}
	
	public PropertyCell[] getPropertiesInMonopoly(String color) {
		PropertyCell[] monopolyCells = 
			new PropertyCell[getPropertyNumberForColor(color)];
		int counter = 0;
		for (int i = 0; i < getCellNumber(); i++) {
			Cell c = getCell(i);
			if(c instanceof PropertyCell) {
				PropertyCell pc = (PropertyCell)c;
				if(pc.getColorGroup().equals(color)) {
					monopolyCells[counter] = pc;
					counter++;
				}
			}
		}
		return monopolyCells;
	}
	
	public int getPropertyNumberForColor(String name) {
		Integer number = colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	public Cell queryCell(String string) {
		Cell ret = null;
		for(int i = 0; i < cells.size(); i++){
			Cell temp = cells.get(i); 
			if(temp.getName().equals(string)) {
				ret = temp;
				break;
			}
		}
		return ret;
	}
	
	public int queryCellIndex(String string){
		for(int i = 0; i < cells.size(); i++){
			Cell temp = cells.get(i); 
			if(temp.getName().equals(string)) {
				return i;
			}
		}
		return -1;
	}

    public void removeCards() {
        communityChestCards.clear();
    }
}
