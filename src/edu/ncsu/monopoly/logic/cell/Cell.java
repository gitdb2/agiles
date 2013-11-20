package edu.ncsu.monopoly.logic.cell;

import edu.ncsu.monopoly.logic.Player;
import edu.ncsu.monopoly.logic.cell.visitor.cell.buyer.IVisitableBuyer;
import edu.ncsu.monopoly.logic.cell.visitor.cell.buyer.IVisitorBuyer;

public abstract class Cell implements IVisitableBuyer{
	public static int InflationParameter = 1;
	public static int NoInflationParameter = 0;
	
	protected boolean available = true;
	protected String name;
	protected Player owner;

	public String getName() {
		return name;
	}

	public Player getOwner() {
		return owner;
	}
	
	public int getPrice() {
		return 0;
	}

	public boolean isAvailable() {
		return available;
	}
	
	public abstract void playAction();

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
    
    public String toString() {
        return name;
    }
    

	@Override
	public void acceptVisitorBuyer(IVisitorBuyer visitor) {
	//No hace nada	
	}

}

