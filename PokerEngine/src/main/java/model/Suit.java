package model;

/**
 * 
 * @author Moheem Ilyas
 *
 */

public enum Suit {
	HEART("heart"), SPADE("spade"), CLUB("club"), DIAMOND("diamond");
	private String s;
	
	Suit(String s){
		setSuit(s);
	}
	
	public void setSuit(String s){
		this.s = s;
	}
	
	public String getSuit(){
		return this.s;
	}
	
	@Override
	public String toString(){
		return this.s + "";
	}
}
