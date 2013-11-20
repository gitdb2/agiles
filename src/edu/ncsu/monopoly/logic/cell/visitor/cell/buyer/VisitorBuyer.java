package edu.ncsu.monopoly.logic.cell.visitor.cell.buyer;

import edu.ncsu.monopoly.logic.Player;
import edu.ncsu.monopoly.logic.cell.BuyableCell;
import edu.ncsu.monopoly.logic.cell.Cell;


public class VisitorBuyer implements IVisitorBuyer{
	Player player;
	
	public VisitorBuyer(Player player) {
		super();
		this.player = player;
	}

	public void visit(Cell visitor){
		BuyableCell tmpCell = (BuyableCell) visitor;
		player.buyProperty(tmpCell, tmpCell.getPrice());
	}
}
