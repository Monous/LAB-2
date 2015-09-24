package PokerPackage;
public class Card{
	private Rank rank;
	private Suit suit;

	public Card(){
		
	}
	
	public Card(Rank r, Suit s) {
		// TODO Auto-generated constructor stub
		this.rank = r;
		this.suit = s;
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