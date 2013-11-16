package edu.ncsu.monopoly.logic.cell;

public abstract class BuyableCell extends Cell {

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

	
}
