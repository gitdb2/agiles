package edu.ncsu.monopoly.logic.cell;

import edu.ncsu.monopoly.logic.GameMaster;
import edu.ncsu.monopoly.logic.Player;
import edu.ncsu.monopoly.logic.cell.visitor.property.collector.IVisitorCollectCells;

public class RailRoadCell extends BuyableCell {
	static private int baseRent;
	static private final String COLOR_GROUP = "RAILROAD";
	static private int price;

	
	
	public RailRoadCell() {
		super(COLOR_GROUP);
	}

	public static void setBaseRent(int baseRent) {
		RailRoadCell.baseRent = baseRent;
	}

	public static void setPrice(int price) {
		RailRoadCell.price = price;
	}
	
	public int getPrice() {
		return RailRoadCell.price;
	}

	public int getRent() {
		return RailRoadCell.baseRent * (int)Math.pow(2, owner.getPropertyNumberForColor(COLOR_GROUP) - 1);
	}
	
	public void playAction() {
		Player currentPlayer = null;
		if(!isAvailable()) {
			currentPlayer = GameMaster.instance().getCurrentPlayer();
			if(owner != currentPlayer) {
				currentPlayer.payRentTo(owner, getRent());
			}
		}
	}
	
	@Override
	public void acceptVisitorCollectCells(IVisitorCollectCells visitor) {
		 visitor.visit(this);
	}
	
}
