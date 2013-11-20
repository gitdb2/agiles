package edu.ncsu.monopoly.logic.cell;

import edu.ncsu.monopoly.logic.cell.visitor.cell.buyer.IVisitorBuyer;
import edu.ncsu.monopoly.logic.cell.visitor.property.collector.IVisitorCollectCells;
import edu.ncsu.monopoly.logic.cell.visitor.property.collector.IVisitableCellCollector;

public abstract class BuyableCell extends Cell implements IVisitableCellCollector{

	protected String colorGroup;
	
	public String getColorGroup(){
		return colorGroup;
	}
	public void setColorGroup(String colorGroup) {
		this.colorGroup = colorGroup;
	}
	public BuyableCell(String colorGroup) {
		super();
		this.colorGroup = colorGroup;
	}
	
	public BuyableCell() {
		super();
	}

//	/**
//	 * No tiene implementacio porque solo interesa que visite PropertyCell	
//	 */
//	public void acceptVisitorCollectProperties(IVisitorCollectProperties visitor){}
//	
	@Override
	public void acceptVisitorBuyer(IVisitorBuyer visitor) {
		visitor.visit(this);
	}
	
}
