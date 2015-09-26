package PokerPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Moheem Ilyas
 * * Created:			9/19/2015
 * Last Modified:		9/25/2015
 * Description:			This class contains the blueprint for each type of hand. The responsibility for making any sort of an evaluation
 * 						for a hand should fall to this object. Even evaluating multiple hands should be done here, since you are ultimately 
 * 						evaluating hand types. 
 * Performance Issues:	A specific method of evaluating the kickers for a FULL_HOUSE needs to be implemented. Currently, the highest card
 * 						is used. For a FULL_HOUSE, the Rank of the three cards should be evaluated first, and then if needed, the rank of the pair.
 * Assignment:          Lab 2
 */
public enum HandType{
	// This first parameter is rankValue (which hand is better than the other), second is pairValue, third is
	// straight, fourth is flush
	NO_PAIR(0, 0, false, false), ONE_PAIR(1, 1, false, false), TWO_PAIR(2, 2, false, false), THREE_OF_A_KIND(3, 3,
			false, false), STRAIGHT(4, 0, true, false), FLUSH(5, 0, false, true), FULL_HOUSE(6, 4, false,
					false), FOUR_OF_A_KIND(7, 5, false, false), STRAIGHT_FLUSH(8, 0, true, true), ROYAL_FLUSH(9, 0,
							true, true);

	private int rankValue;
	private int pairValue;
	private boolean straight;
	private boolean flush;
	
	/**
	 * @author Moheem Ilyas
	 * @param rankValue
	 * @param pairValue
	 * @param straight
	 * @param flush
	 * 
	 */
	HandType(int rankValue, int pairValue, boolean straight, boolean flush) {
		this.rankValue = rankValue;
		this.pairValue = pairValue;
		this.straight = straight;
		this.flush = flush;
	}

	public boolean getStraightValue() {
		return this.straight;
	}

	public boolean getFlushValue() {
		return this.flush;
	}

	public int getPairValue() {
		return this.pairValue;
	}
	
	public int getRankValue(){
		return this.rankValue;
	}
	
	/**
	 * @author Moheem Ilyas
	 * @param hand
	 * @return int The method will return the integer corresponding to what type of pair it is.
	 * 
	 * Description: This method determines what type of pair hand the user has, e.g. no pair, two of a kind, three of a kind, full house
	 * 			 	and four of a kind. The value being returned corresponds to data enum HandType object with same pairValue. 
	 * 				
	 */
	public static int determinePairs(Hand hand) {
		// Four of a kind
		if (hand.getSortedVals().containsValue(4)){
			return 5;
		}
		// Full house
		else if (hand.getSortedVals().containsValue(3) && hand.getSortedVals().containsValue(2))
			return 4;
		// Three of a kind
		else if (hand.getSortedVals().containsValue(3))
			return 3;
		// Two pairs
		else if (hand.getSortedVals().containsValue(2) && hand.getSortedVals().size() == 3)
			return 2;
		// One pair
		else if (hand.getSortedVals().containsValue(2))
			return 1;
		// Shit out of luck
		else 
			return 0;
	}
	
	/**
	 * @author Moheem Ilyas
	 * @param hand
	 * @return true if the hand is a flush; false if the hand is not a flush.
	 * Notes: Since the frequency for each suit in a hand is a data member for a Hand object, we can simply check if
	 * 		  the size of the map containing the suits.
	 */
	public static boolean determineFlush(Hand hand) {
		if (hand.getSuitsInHand().size() == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * @author Moheem Ilyas
	 * @param hand
	 * @return true if the hand is a straight; false if the hand is not a straight
	 */
	public static boolean determineStraight(Hand hand) {
		// Can't be a straight with less than five cards. 
		if (hand.getSortedVals().size() != 5) {
			return false;
		}
		Object[] array = hand.getSortedVals().keySet().toArray();

		// The general methodology for determining straights does not work for an ace, two, three, four, five straight.
		if (array[0] == Rank.TWO && array[1] == Rank.THREE && array[2] == Rank.FOUR && array[3] == Rank.FIVE
				&& array[4] == Rank.ACE) {
			return true;
		}

		// General methodology for determining a straight...
		// Need to be able to compare the value at i and i + 1...hence the
		// array
		// Since sortedRankInHand is sorted by key (the key is a Rank) ascending, the array will
		// already be sorted
		// The reason for the minus 1 is because there may be an
		// ArrayIndexOutOfBounds exception thrown if you go through entire
		// array.
		for (int i = 0; i < array.length - 1; i++) {
			Rank r1 = (Rank) array[i];
			Rank r2 = (Rank) array[i + 1];
			if ((r2.getRank() - r1.getRank()) != 1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @author Moheem Ilyas
	 * @param handType
	 * @param hand
	 * @return kickerPossibilities
	 * 
	 * Description: Although this methodology is a bit mundane, it really must come down to checking each hand.
	 * 				I did not use a switch statement because code for determining kickers for certain pair value will be the same...one nice or statement. 
	 */
	public static List<Rank> determineKickerPossibilities(HandType handType, Hand hand){
		List<Rank> kickerPossibilities = new ArrayList<Rank>();
		 
		if (handType == HandType.NO_PAIR){
			for (Object r : hand.getSortedVals().keySet().toArray()){
				Rank rank = (Rank) r;
				kickerPossibilities.add(rank);
			}
		}
		// ...Nice or statment
		else if (handType == HandType.ONE_PAIR || handType == HandType.TWO_PAIR || handType == HandType.THREE_OF_A_KIND || handType == HandType.FOUR_OF_A_KIND){
			for (Object r : hand.getSortedVals().keySet().toArray()){
				Rank rank = (Rank) r;
				// Using .equals because the value for the key is an OBJECT Integer
				if (hand.getSortedVals().get(rank).equals(1))
					kickerPossibilities.add(rank);
			}
		}
		
		// This is actually a bullshit way to do it, but don't have the time to experiment.
		else if (handType == HandType.FULL_HOUSE){
			Rank lowerRank;
			Rank higherRank;
			if ( hand.getSortedVals().get(hand.getSortedVals().firstKey()) == 2){
				lowerRank = (Rank) hand.getSortedVals().firstKey();
				higherRank = (Rank) hand.getSortedVals().lastKey();
			} else {
				higherRank = (Rank) hand.getSortedVals().firstKey();
				lowerRank = (Rank) hand.getSortedVals().lastKey();
			}
			kickerPossibilities.add(lowerRank);
			kickerPossibilities.add(higherRank);
		}
	
		else if (handType == HandType.FLUSH){
			// Still CANNOT just use the high card in the hand because more than one person can have a flush with an ACE, or TWO, or THREE....
			for (Object r : hand.getSortedVals().keySet().toArray()){
				Rank rank = (Rank) r;
				kickerPossibilities.add(rank);
			}
		}
		// As long as the hand is a straight, you don't need a separate case for a STRAIGHT_FLUSH because suit won't matter for precedence
		// in terms of kickers, numerical value does.
		else if (handType == HandType.STRAIGHT || handType == HandType.STRAIGHT_FLUSH){
			// The if statement if for that damned TWO, THREE, FOUR, FIVE, ACE straight
			if ((Rank) hand.getSortedVals().firstKey() == Rank.TWO)
				// Have to get the sorted vals (Ace is at the end), get the keys, toArray, get the fourth value because that is the "high card", i.e. 5
				kickerPossibilities.add((Rank) hand.getSortedVals().keySet().toArray()[4]);
			else kickerPossibilities.add((Rank) hand.getSortedVals().lastKey());
		}
		
		// For the holy of holies
		else if (handType == HandType.ROYAL_FLUSH){
			for (int i = 0; i < 5; i++){
				kickerPossibilities.add(Rank.ACE);
			}
		}
		
		return kickerPossibilities;
	}
	
	/**
	 * @author Moheem Ilyas
	 * @param hand
	 * Description:  This method actually determines what type of hand the player has. It
		 * may be the case that the player's hand qualifies as two HandType's,
		 * e.g. straight and flush... to account for this, the possibleHands
		 * array will hold all the possible types temporarily and the highest
		 * hand type out of the list will be obtained to set handType.
	 */
	public static void checkHand(Hand hand) {
		List<HandType> possibleHands = new ArrayList<>();
		// The highCard and lowCard are used to tell the difference between a straight flush and a royal flush
		Rank highCard = (Rank) hand.getSortedVals().keySet().toArray()[hand.getSortedVals().size() - 1];
		Rank lowCard = (Rank) hand.getSortedVals().keySet().toArray()[0];
		// Comparing cards in the players hand to see if they match any of the enum HandType.
		for (HandType ht : HandType.values()) {
			if (determineStraight(hand) == ht.getStraightValue() && determineFlush(hand) == ht.getFlushValue()
					&& determinePairs(hand) == ht.getPairValue()) {
				if (ht != HandType.ROYAL_FLUSH && ht != HandType.STRAIGHT_FLUSH){
					possibleHands.add(ht);
				}
				else {
					if (highCard == Rank.ACE && lowCard == Rank.TEN)
						possibleHands.add(HandType.ROYAL_FLUSH);
					else{
						possibleHands.add(HandType.STRAIGHT_FLUSH);
					}
				}
			}
		}
		judgeHand(possibleHands);
		HandType bestHandType = possibleHands.get(possibleHands.size()-1);
		List<Rank> kickerPossibilities = determineKickerPossibilities(bestHandType, hand);
		hand.setHandType(bestHandType);
		hand.setKickerPossibilities(kickerPossibilities);
	}

	/**
	 * @author Moheem Ilyas
	 * @param hands
	 * @return This method returns the ArrayList of best hand positions.
	 * Description:	The methodology goes as follows...Get the HandType for all hands in the List argument and get
	 * 				the kickerPossibilities for all hands, sort the HandType's to get the best one in the last position,
	 * 				traverse through the List of hands passed in and find any hand whose HandType matches that of the best HandType,
	 * 				remove the kicker hands of the hands whose HandType does not match the best HandType, if there is more than one
	 * 				hand whose HandType matches the best HandType, compare the kickers.
	 */
	public static ArrayList<Integer> judgeHands(List<Hand> hands) {
		List<HandType> sortedHandTypes = new ArrayList<HandType>();
		List<List<Rank>> sortedKickerHands = new ArrayList<List<Rank>>();
		ArrayList<Integer> bestHandPositions = new ArrayList<Integer>();
		for (Hand h : hands){
			sortedHandTypes.add(h.getHandType());
			// The position of each kicker in this list corresponds to the 
			sortedKickerHands.add(h.getKickerPossibilities());
		}
		// Sorting to get the best hand type to the end
		Collections.sort(sortedHandTypes);
		for (int i = 0; i < hands.size() ; ++i){
			// Adding the position of any hand in hands that has the HandType for the HandType that is at
			// the ended of sortedHandTypes (because that will be the best hand.)
			if (hands.get(i).getHandType().equals(sortedHandTypes.get(sortedHandTypes.size() - 1)))
				bestHandPositions.add(i);
			else{
				// hands.get(i).getKickerPossibilities() gets the kicker possibilities of the hand because
				// the hand in this iteration does not match the best HandType...use that to find the index of the
				// kicker array that needs to be removed.
				sortedKickerHands.remove(sortedKickerHands.indexOf(hands.get(i).getKickerPossibilities()));
			}
		}
		if (bestHandPositions.size() > 1){
			for (int i = 0; i < sortedKickerHands.size() - 1; i++){
				if (Rank.compareRank(sortedKickerHands.get(i), sortedKickerHands.get(i + 1)) == 1) {
					bestHandPositions.remove(i + 1);
				}
				else if (Rank.compareRank(sortedKickerHands.get(i), sortedKickerHands.get(i + 1)) == -1){
					bestHandPositions.remove(i);
				}
			}
		}
		return bestHandPositions;
	}
	
	// This method is used to determine the hand type of a SINGLE hand.
	public static void judgeHand(List<HandType> handTypeArray) {
		Collections.sort(handTypeArray);
	}
	
}
