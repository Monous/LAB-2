package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Moheem Ilyas Created: 9/19/2015 Last Modified: 9/25/2015 Description:
 *         This class specifies what a hand is. It will NOT evaluate itself, but
 *         instead know what its kickers are, how many of each Suit there is,
 *         and how many of each Rank there is. Assignment: Lab 2
 * 
 *         TO TEST determine<INSERT CASE HERE> METHODS, CREATE TEST CONSTRUCTORS
 *         THAT WILL EXPLICITY CREATE A CERTAIN TYPE OF HAND, E.G. FLUSH,
 *         STRAIGHT ETC..
 * 
 */

public class Hand {

	boolean jokerCard;
	private HandType handType;
	private List<Hand> combinations = new ArrayList<Hand>();
	private ArrayList<Card> hand = new ArrayList<Card>();
	// suitsInHand holds the suit type (Suit key), and the **FREQUENCY** of that
	private Map<Suit, Integer> suitsInHand = new HashMap<Suit, Integer>();
	// sortedRankInHand holds the ranks (Key) (Ace, two, three...) and the
	// **FREQUENCY** (Integer value) of that rank.
	// sortedRankInHand is a TreeMap so that I can have the card ranks
	// sorted...which makes things easier down the road
	private TreeMap<Rank, Integer> sortedRankInHand = new TreeMap<Rank, Integer>();

	// Have ALL the possible kickers in one container. The kickers in the
	// container will be determined by what type of hand you have.
	// The reason why kickerPossibilities is in this class instead of HandType
	// (so it could be like, flush + kickerPossibilities) is
	// because I wanted to use the HandType of the Hand to determine what I
	// should have as my kickers.
	private List<Rank> kickerPossibilities = new ArrayList<Rank>();

	// This should never be used explicitly

	public Hand() {
	}

	/**
	 * @author Moheem Ilyas This is the constructor that should mainly be used.
	 */
	public Hand(Deck deck) throws DeckOutOfCardsException {
		try {
			for (int i = 0; i < 5; i++) {

				Card tempCard = deck.getCard();
				this.hand.add(tempCard);
				if (tempCard.getRank() == Rank.JOKER) {
					this.jokerCard = true;
				}
			}
			if (this.jokerCard) {
				handleJokers();
			} else {
				this.initSuitsAndSorted();
				HandType.checkHand(this);
			}

		} catch (DeckOutOfCardsException e) {
			throw new DeckOutOfCardsException();
		}
	}

	// Test constructor
	public Hand(Card c1, Card c2, Card c3, Card c4, Card c5) {
		// NOTE: using the ArrayList add method instead of this.addCard method because this.initSuitsAndSorted
		// is called either in handleJokers or in the else clause
		this.hand.add(c1);
		this.hand.add(c2);
		this.hand.add(c3);
		this.hand.add(c4);
		this.hand.add(c5);

		if (c1.getRank() == Rank.JOKER || c2.getRank() == Rank.JOKER || c3.getRank() == Rank.JOKER
				|| c3.getRank() == Rank.JOKER || c4.getRank() == Rank.JOKER || 
				c5.getRank() == Rank.JOKER) {
			this.jokerCard = true;
			handleJokers();
		} else {
			this.initSuitsAndSorted();
			HandType.checkHand(this);
		}
	}

	public HandType getHandType() {
		return handType;
	}

	public void setHandType(HandType handType) {
		this.handType = handType;
	}

	public TreeMap<Rank, Integer> getSortedVals() {
		return this.sortedRankInHand;
	}

	public Map<Suit, Integer> getSuitsInHand() {
		return this.suitsInHand;
	}

	public List<Rank> getKickerPossibilities() {
		return this.kickerPossibilities;
	}

	public void setKickerPossibilities(List<Rank> kickers) {
		this.kickerPossibilities = kickers;
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	public boolean hasJoker(){
		return this.jokerCard;
	}
	
	public void setJoker (boolean isTrue){
		this.jokerCard = isTrue;
	}

	// Testing method
	public void printHand() {
		for (int i = 0; i < 5; i++) {
			System.out.println(this.hand.get(i));
		}
	}

	// Testing method
	public void printHandTypeVals() {
		System.out.println(this.suitsInHand);
		System.out.println(this.sortedRankInHand);
	}

	/**
	 * @author Moheem Ilyas Desctiption: The purpose of this method is to get
	 *         the frequency of a suit and a Rank for a Hand.
	 */
	public void initSuitsAndSorted() {
		// The containers need to be cleared, otherwise the handleJokers method
		// will improperly add values in the checking of ranks and
		// suits/determining hand type
		// for loop (in handleJokers()).
		this.suitsInHand.clear();
		this.sortedRankInHand.clear();

		for (Card c : this.hand) {
			if (this.suitsInHand.containsKey(c.getSuit())) {
				int curVal = this.suitsInHand.get(c.getSuit());
				curVal += 1;
				this.suitsInHand.put(c.getSuit(), curVal);
			} else
				this.suitsInHand.put(c.getSuit(), 1);
			if (this.sortedRankInHand.containsKey(c.getRank())) {
				int curVal = this.sortedRankInHand.get(c.getRank());
				curVal += 1;
				this.sortedRankInHand.put(c.getRank(), curVal);
			} else
				this.sortedRankInHand.put(c.getRank(), 1);
		}
	}

	public void addCard(Card c) {
		this.hand.add(c);
		//this.initSuitsAndSorted();
	}

	public void handleJokers() {
		ArrayList<Integer> positions = new ArrayList<Integer>();
		int numOfJokers = 0;
		List<Card> allCards = new ArrayList<Card>();
		for (Card c : hand) {
			if (c.getRank() == Rank.JOKER) {
				++numOfJokers;
				positions.add(hand.indexOf(c));
			}
		}

		for (int i = 0; i < Math.pow(52.0, numOfJokers); i++) {
			Hand tempHand = new Hand();
			for (Card c : hand) {
				tempHand.addCard(c);
			}
			combinations.add(tempHand);
		}

		int nextIndex = 0;
		int numOfJokersCopy = numOfJokers;
		do {
			int i = 0;
			int pos = positions.get(nextIndex);
			int curIndex = positions.indexOf(pos);
			allCards.clear();

			for (Rank rank : Rank.values()) {
				if (rank != Rank.JOKER) {
					for (Suit suit : Suit.values()) {
						Card tempCard = new Card(rank, suit);
						for (int m = 0; m < Math.pow(52.0, curIndex); m++) {
							allCards.add(tempCard);
						}
					}
				}
			}

			for (int j = 0; j < Math.pow(52.0, numOfJokers); j++) {
				combinations.get(j).setJoker(true);
				combinations.get(j).getHand().set(pos, allCards.get(i));
				i++;
				if (i == Math.pow(52.0, (curIndex + 1)))
					i = 0;

			}

			// Checking the ranks and suits in each hand...evaluating the hand
			for (Hand h : combinations) {
				h.initSuitsAndSorted();
				HandType.checkHand(h);
			}
			++nextIndex;
			--numOfJokersCopy;
		} while (numOfJokersCopy > 0);

		ArrayList<Integer> pos = new ArrayList<Integer>();
		pos = HandType.judgeHands(combinations);

		setEqual(combinations.get(pos.get(0)));
	}

	public Hand getHandInCombos(int pos) {
		return this.combinations.get(pos);
	}

	public List<Hand> getCombos() {
		return this.combinations;
	}

	public void setEqual(Hand hand) {
		this.hand = hand.getHand();
		this.handType = hand.getHandType();
		this.kickerPossibilities = hand.getKickerPossibilities();
		this.suitsInHand = hand.getSuitsInHand();
		this.sortedRankInHand = hand.getSortedVals();
	}

	@Override
	public String toString() {
		return this.hand + "  ";
	}
}
