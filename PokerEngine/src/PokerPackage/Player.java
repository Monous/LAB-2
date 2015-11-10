package PokerPackage;

import java.util.UUID;


import domain.PlayerDomain;
public class Player extends PlayerDomain{

	private Hand hand;
	
	public Player(){
		super();
	}
	
	public Hand getHand(){
		return this.hand;
	}
	
	public void setHand(Deck deck){
		try {
			this.hand = new Hand(deck);
		} catch (DeckOutOfCardsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
