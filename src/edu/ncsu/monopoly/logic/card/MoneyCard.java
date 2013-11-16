package edu.ncsu.monopoly.logic.card;

import edu.ncsu.monopoly.GameMaster;
import edu.ncsu.monopoly.Player;

public class MoneyCard extends Card {
    private int amount;
    private int cardType;
    
    private String label;
    
    public MoneyCard(String label, int amount, int cardType){
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }

    public int getCardType() {
        return cardType;
    }

    public String getLabel() {
        return label;
    }
}