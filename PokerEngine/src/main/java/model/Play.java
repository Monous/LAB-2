package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import domain.PlayDomain;



/**
 * @author Moheem Ilyas
 * 
 *         Created: 9/19/2015 Last Modified: 9/25/2015 Description: This class
 *         serves to play an actual game of poker. The essential features of
 *         this class is to prompt the user for the deck size and the number of
 *         players. Hands will then be generated for each player, printed to the
 *         console, and evaluated. The evaluation will print to the console.
 *         Performance Issues: (1) Error handling is not done for the user
 *         input. (2) The DeckOutOfCardsException caught by the main class will
 *         only be caught once. If another error of this type occurs, program
 *         crashes. (3) The statistics for the hand probability do not seem to
 *         match up. There has been no quantitative analysis as of yet, however.
 *         Assignment: Lab 2
 *
 */
public class Play extends PlayDomain {
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Player> winners = new ArrayList<Player>();
	private eGame gameType;
	private Deck deck;
	private ArrayList<Card> communityCards = new ArrayList<Card>();

	public Play(eGame gameType) {
		this.gameType = gameType;
	}

	public void addPlayer(Player p) {
		this.players.add(p);
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void setGameType(eGame gameType, ArrayList<Player> players) {
		this.players = players;
		this.gameType = gameType;
	}

	public eGame getEGame() {
		return this.gameType;
	}

	public ArrayList<Player> getPlayer() {
		return this.players;
	}

	public ArrayList<Player> getWinners() {
		return this.winners;
	}

	public ArrayList<Card> getCommunityCards() {
		return this.communityCards;
	}

	public void play() {
		switch (this.gameType) {
		case FIVECARDSTUD:
			playFiveStud();
			break;
		case FIVECARDJOKERPOKER:
			playFiveJoker();
			break;
		case FIVECARDWILDPOKER:
			playFiveWild();
			break;
		case FIVECARDDRAW:
			playFiveDraw();
			break;
		case SEVENCARDDRAW:
			playSevenDraw();
			break;
		case HOLDEM:
			playHoldEm();
			break;
		case OMAHA:
			playOmaha();
			break;
		}
	}

	private void playFiveStud() {
		this.deck = new Deck(1, 0, 0);
		ArrayList<Hand> playerHands = new ArrayList<Hand>();
		for (Player p : this.players) {
			p.setHand(this.deck);
			playerHands.add(p.getHand());
		}

		ArrayList<Integer> positions = HandType.judgeHands(playerHands);
		for (Integer i : positions) {
			winners.add(players.get(i));
		}
	}

	private void playFiveDraw() {

	}

	private void playFiveJoker() {

	}

	private void playFiveWild() {

	}

	private void playSevenDraw() {

	}

	private void playHoldEm() {
		this.deck = new Deck();
		List<Hand> playerHands = new ArrayList<Hand>();
		// Dealing a card to each player IN TURN
		initializeHands();

		// https://code.google.com/p/combinatoricslib/
		// Create the initial vector
		// This will be all the variations you can make with the community
		// cards
		// Since we know this is holdem, we know that there will be five
		// community cards.
		ICombinatoricsVector<Card> initialVector = Factory.createVector(new Card[] { communityCards.get(0),
				communityCards.get(1), communityCards.get(2), communityCards.get(3), communityCards.get(4) });

		// Create a simple combination generator to generate 3-combinations
		// of the initial vector
		Generator<Card> gen = Factory.createSimpleCombinationGenerator(initialVector, 3);


		for (Player p : this.players) {
			List<Hand> possibleHands = new ArrayList<Hand>();

			for (ICombinatoricsVector<Card> c : gen) {
				ArrayList<Card> tempCards = new ArrayList<Card>();
				for (Card alreadyInHand : p.getHand().getHand()) {
					tempCards.add(alreadyInHand);
				}
				for (Card vectorC : c) {
					tempCards.add(vectorC);
				}
				// Hard coded because we know that the evaluation will be
				// based on five cards (this goes for all games)
				/*
				 * NOTE: when dealing the cards, only deal the cards that are
				 * specified by eGame!!
				 */
				Hand tempHand = new Hand(tempCards.get(0), tempCards.get(1), tempCards.get(2), tempCards.get(3),
						tempCards.get(4));
				possibleHands.add(tempHand);
				// System.out.println(tempCards.get(0));
			}

			p.setHand(possibleHands.get(HandType.judgeHands(possibleHands).get(0)));
		}

		for (Player p : this.players) {
			playerHands.add(p.getHand());
		}

		for (Integer winnerPos : HandType.judgeHands(playerHands)) {
			this.winners.add(this.players.get(winnerPos));
		}
	}

	private void playOmaha() {

		this.deck = new Deck();
		List<Hand> playerHands = new ArrayList<Hand>();

		// Dealing a card to each player IN TURN
		initializeHands();

		// Generating the combinations for the community cards
		ICombinatoricsVector<Card> initialVectorForComm = Factory.createVector(new Card[] { communityCards.get(0),
				communityCards.get(1), communityCards.get(2), communityCards.get(3), communityCards.get(4) });

		Generator<Card> genComm = Factory.createSimpleCombinationGenerator(initialVectorForComm, 3);

		// We'll use this map to compare the hands since we can't replace the
		// players hand with the best possible hand
		Map<Player, Hand> tempMap = new HashMap<Player, Hand>();
		for (Player p : this.players) {

			ArrayList<Hand> tempPlayerHands = new ArrayList<Hand>();

			// Generating the combinations for the hand for the player
			// Each player will already have four cards in the hand
			ICombinatoricsVector<Card> initialVectorHand = Factory
					.createVector(new Card[] { p.getHand().getHand().get(0), p.getHand().getHand().get(1),
							p.getHand().getHand().get(2), p.getHand().getHand().get(3) });

			Generator<Card> genHand = Factory.createSimpleCombinationGenerator(initialVectorHand, 2);

			for (ICombinatoricsVector<Card> handVect : genHand) {
				for (ICombinatoricsVector<Card> comVect : genComm) {
					ArrayList<Card> tempCards = new ArrayList<Card>();
					for (Card c : handVect) {
						tempCards.add(c);
					}

					for (Card c2 : comVect) {
						tempCards.add(c2);
					}

					Hand tempHand = new Hand(tempCards.get(0), tempCards.get(1), tempCards.get(2), tempCards.get(3),
							tempCards.get(4));
					tempPlayerHands.add(tempHand);
				}
			}
			// Getting the best hand out of the community/player hand
			// combinations
			Hand bestHand = tempPlayerHands.get(HandType.judgeHands(tempPlayerHands).get(0));
			p.getHand().setHandType(bestHand.getHandType());
			tempMap.put(p, bestHand);
		}

		// List<Hand> tempHands = new ArrayList<Hand>();
		for (Hand h : tempMap.values()) {
			playerHands.add(h);
		}

		for (Integer i : HandType.judgeHands(playerHands)) {
			for (Player p : tempMap.keySet()) {
				// If the players' hand in the map equals the hand in tempHands
				// (positions of best hands array) at index i, add the player to
				// the winners array
				// This relies on pass by ref.
				if (tempMap.get(p) == playerHands.get(i))
					winners.add(p);
			}
		}

	}

	private void initializeHands() {
		try {
			for (int i = -1; i < this.gameType.getCardsDealt(); i++) {
				for (Player p : this.players) {
					if (i == -1)
						p.setHand(new Hand());
					else
						p.getHand().addCard(this.deck.getCard());
				}
			}

			// The number of cards that will be added to the communityCards
			// array
			// is specified in eGame.java
			for (int i = 0; i < this.gameType.getCommunityCards(); i++) {
				this.communityCards.add(this.deck.getCard());
			}
		} catch (DeckOutOfCardsException e) {
			e.printStackTrace();
		}
	}
	
}
