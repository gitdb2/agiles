package edu.ncsu.monopoly.logic.cell;

import edu.ncsu.monopoly.logic.GameMaster;
import edu.ncsu.monopoly.logic.Player;

public class PropertyCell extends BuyableCell {
	
	private int housePrice;
	private int numHouses;
	private int rent;
	private int sellPrice;

	

	public int getHousePrice() {
		return housePrice;
	}

	public int getNumHouses() {
		return numHouses;
	}
    
    public int getPrice() {
		return sellPrice;
	}

	public int getRent(int parameter) {
		if (parameter == 0){
			return getParameterlessRent();
		}
		else{
			int rentToCharge = rent * Cell.InflationParameter;
			String [] monopolies = owner.getMonopolies();
			for(int i = 0; i < monopolies.length; i++) {
				if(monopolies[i].equals(colorGroup)) {
					rentToCharge = rent * 2;
				}
			}
			if(numHouses > 0) {
				rentToCharge = rent * (numHouses + 1);
			}
			return rentToCharge;
		}
		
	}

	private int getParameterlessRent() {
		int rentToCharge = rent;
		String [] monopolies = owner.getMonopolies();
		for(int i = 0; i < monopolies.length; i++) {
			if(monopolies[i].equals(colorGroup)) {
				rentToCharge = rent * 2;
			}
		}
		if(numHouses > 0) {
			rentToCharge = rent * (numHouses + 1);
		}
		return rentToCharge;
	}
	

	public void playAction() {
		Player currentPlayer = null;
		if(!isAvailable()) {
			currentPlayer = GameMaster.instance().getCurrentPlayer();
			if(owner != currentPlayer) {
				currentPlayer.payRentTo(owner, getRent(Cell.InflationParameter));
			}
		}
	}

	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	public void setNumHouses(int numHouses) {
		this.numHouses = numHouses;
	}

	public void setPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}
}
