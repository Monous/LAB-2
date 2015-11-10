package PokerPackage;

public enum eGame {
	// cards dealt; community cards; jokers; wilds; 
	FIVECARDSTUD(5, 0, false, false), FIVECARDJOKERPOKER(5, 0, true, false),
	FIVECARDWILDPOKER(5, 0, false, true), FIVECARDDRAW(5, 0, false, false), SEVENCARDDRAW(7, 0, false, false),
	HOLDEM(2, 5, false, false), OMAHA(2, 5, false, false);
	
	private int cardsDealt;
	private int communityCards;
	private boolean hasJokers;
	private boolean hasWilds;
	
	eGame(int cardsDealt, int communityCards, boolean hasJokers, boolean hasWilds){
		this.cardsDealt = cardsDealt;
		this.communityCards = communityCards;
		this.hasJokers = hasJokers;
		this.hasWilds = hasWilds;
	}
	
	public int getCardsDealt() {
		return cardsDealt;
	}

	public int getCommunityCards() {
		return communityCards;
	}

	public boolean isHasJokers() {
		return hasJokers;
	}

	public boolean isHasWilds() {
		return hasWilds;
	}
}
