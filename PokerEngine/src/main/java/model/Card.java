package model;

/**
 * 
 * @author Moheem Ilyas
 * 
 *
 */
public class Card{
	private Rank rank;
	private Suit suit;
	private boolean wild;

	public Card(){
		
	}
	
	public Card(Rank r, Suit s) {
		this.rank = r;
		this.suit = s;
	}
	
	/*
	 * This constructor is for Jokers. Jokers have no suit.
	 */
	public Card(Rank r){
		this.rank = r;
	}
	
	// Testing method
	public void setRank(Rank r){
		this.rank = r;
	}
	// Testing method
	public void setSuit(Suit s){
		this.suit = s;
	}
	
	public Suit getSuit(){
		return this.suit;
	}
	
	public Rank getRank(){
		return this.rank;
	}
	@Override
	public String toString(){
		return this.rank + "  " + this.suit;
	}
}
