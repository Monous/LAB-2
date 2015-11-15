package model;

import java.util.UUID;


import domain.PlayerDomain;
public class Player extends PlayerDomain{

	private Hand hand;
	
	public Player(){
		super();
		this.hand = new Hand();
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
	
	public void setHand(Hand hand){
		this.hand = hand;
	}
}
