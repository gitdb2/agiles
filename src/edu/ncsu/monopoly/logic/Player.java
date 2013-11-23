package edu.ncsu.monopoly.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import edu.ncsu.monopoly.logic.cell.BuyableCell;
import edu.ncsu.monopoly.logic.cell.Cell;
import edu.ncsu.monopoly.logic.cell.JailCell;
import edu.ncsu.monopoly.logic.cell.PropertyCell;
import edu.ncsu.monopoly.logic.cell.visitor.cell.buyer.VisitorBuyer;
import edu.ncsu.monopoly.logic.cell.visitor.property.collector.VisitorCollectCells;
import edu.ncsu.monopoly.logic.gameboarad.GameBoard;


public class Player {
	//the key of colorGroups is the name of the color group.
//	private Hashtable<String, Integer> colorGroups = new Hashtable<String, Integer>();
	private boolean inJail;
	private int money;
	private String name;

	private Cell position;
	
	
	private Hashtable<String,  ArrayList<BuyableCell>> colorGroups = new Hashtable<String,  ArrayList<BuyableCell>>();
	
	private ArrayList<BuyableCell> purchasedCells = new ArrayList<BuyableCell>();
	
	public Player() {
		GameBoard gb = GameMaster.instance().getGameBoard();
		inJail = false;
		if(gb != null) {
			position = gb.queryCell("Go");
		}
	}

    public void buyProperty(BuyableCell cell, int amount) {
    	cell.setOwner(this);
    	purchasedCells.add(cell);
    	addToGroupColors(cell);
        setMoney(getMoney() - amount);
    }
    
    /**
     * Agrega una celda al mapa de colores y si no existe crea la lista y la inserta con el color como clave
     * @param cell
     */
	private void addToGroupColors(BuyableCell cell) {
		ArrayList<BuyableCell> tempCelsByColor = colorGroups.get(cell.getColorGroup());
    	if(tempCelsByColor == null){
    		tempCelsByColor = new ArrayList<BuyableCell>();
    		colorGroups.put(cell.getColorGroup(), tempCelsByColor);
    	}
    	tempCelsByColor.add(cell);
	}
	
	public void removeFromGroupColors(BuyableCell cell){
		List<BuyableCell> coloredCells =  colorGroups.get(cell.getColorGroup());
		if(coloredCells!= null){
			coloredCells.remove(cell);
		}
	}
	
	public boolean canBuyHouse() {
		return (getMonopolies().length != 0);
	}

	public boolean checkProperty(String property) {
		for(int i=0;i<purchasedCells.size();i++) {
			Cell cell = (Cell)purchasedCells.get(i);
			if(cell.getName().equals(property)) {
				return true;
			}
		}
		return false;
		
	}
	
	public void exchangeProperty(Player player) {

		for (PropertyCell cell : getProperties()) {
			cell.setOwner(player);
			if(player == null) {
				cell.setAvailable(true);
				cell.setNumHouses(0);
			}
			else {
				player.purchasedCells.add(cell);
				addToGroupColors(cell);
			}
		}

		purchasedCells.clear();
	}
	/**
	 * Metodo para obtener las celdas de tipo PropertyCell mediante visitor
	 * @return
	 */
	private List<PropertyCell> getProperties() {
		VisitorCollectCells visitor = new VisitorCollectCells();
		for (BuyableCell cell : purchasedCells) {
			cell.acceptVisitorCollectCells(visitor);
		}
		return visitor.getProperties();
	}
    
    public Cell[] getAllProperties() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        list.addAll(purchasedCells);
        return list.toArray(new Cell[list.size()]);
    }

	public int getMoney() {
		return this.money;
	}
	
	public String[] getMonopolies() {
		ArrayList<String> monopolies = new ArrayList<String>();
				
		Set<String> colorSet = new HashSet<String>();
		for (BuyableCell cell : getProperties()) {
			colorSet.add(cell.getColorGroup());
		}
		
		for (String color : colorSet) {
			int num = getPropertyNumberForColor(color);
			GameBoard gameBoard = GameMaster.instance().getGameBoard();
			if(num == gameBoard.getPropertyNumberForColor(color)) {
				monopolies.add(color);
			}
		}
		return (String[])monopolies.toArray(new String[monopolies.size()]);
	}

	public String getName() {
		return name;
	}

	public void getOutOfJail() {
		money -= JailCell.BAIL;
		if(isBankrupt()) {
			money = 0;
			exchangeProperty(null);
		}
		inJail = false;
		GameMaster.instance().updateGUI();
	}

	public Cell getPosition() {
		return this.position;
	}
	
	/**
	 * SOLO USADO EN TESTS
	 * @return
	 */
	@Deprecated
	public PropertyCell getProperty(int index) {
		return getProperties().get(index);
	}
	
	/**
	 * SOLO USADO EN TESTS
	 * @return
	 */
	@Deprecated
	public int getPropertyNumber() {
		return getProperties().size();
	}

	
	public int getPropertyNumberForColor(String name) {
		
		List<BuyableCell> list= colorGroups.get(name);
		if(list != null) {
			return list.size();
		}
		return 0;
	}

	public boolean isBankrupt() {
		return money <= 0;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void payRentTo(Player owner, int rentValue) {
		if(money < rentValue) {
			owner.money += money;
			money -= rentValue;
		}
		else {
			money -= rentValue;
			owner.money +=rentValue;
		}
		if(isBankrupt()) {
			money = 0;
			exchangeProperty(owner);
		}
	}
	
	public void purchase() {
		if(getPosition().isAvailable()) {
			Cell cell = getPosition();
			cell.setAvailable(false);

			purchaseCell(cell);
		
		}
	}
	
	/**
	 * 
	 * @param selectedMonopoly es el color
	 * @param houses cantidad de casa
	 */
	public void purchaseHouse(String selectedMonopoly, int houses) {
		GameBoard gb = GameMaster.instance().getGameBoard();
		PropertyCell[] cells = gb.getPropertiesInMonopoly(selectedMonopoly);
		if((money >= (cells.length * (cells[0].getHousePrice() * houses)))) {
			for(int i = 0; i < cells.length; i++) {
				int newNumber = cells[i].getNumHouses() + houses;
				if (newNumber <= 5) {
					cells[i].setNumHouses(newNumber);
					this.setMoney(money - (cells[i].getHousePrice() * houses));
					GameMaster.instance().updateGUI();
				}
			}
		}
	}
	
	/**
	 * Metodo generico para la compra de una celda
	 * @param cell
	 */
	private void purchaseCell(Cell cell) {
		VisitorBuyer visitor = new VisitorBuyer(this);
		cell.acceptVisitorBuyer(visitor);
	}

	/**
	 * 
	 * @param cell
	 * @param amount
	 */
    public void sellCell(BuyableCell cell, int amount) {
        cell.setOwner(null);
        
        removeFromGroupColors(cell);
        
        purchasedCells.remove(cell);
        setMoney(getMoney() + amount);
    }

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(Cell newPosition) {
		this.position = newPosition;
	}

    public String toString() {
        return name;
    }
    
    public void resetProperty() {
    	purchasedCells.clear();
    	colorGroups.clear();
	}

    /**
     * Solo para tests
     * @return
     */
    public int numberOfRR() {

    	VisitorCollectCells visitor = new VisitorCollectCells();
    	for (BuyableCell cell : purchasedCells) {
    		cell.acceptVisitorCollectCells(visitor);
    	}
    	return visitor.getRailRoads().size();
    }

	public int numberOfUtil() {
	 	VisitorCollectCells visitor = new VisitorCollectCells();
    	for (BuyableCell cell : purchasedCells) {
    		cell.acceptVisitorCollectCells(visitor);
    	}
    	return visitor.getUtilities().size();
	}
}
