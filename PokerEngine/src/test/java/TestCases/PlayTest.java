package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Card;
import model.Deck;
import model.HandType;
import model.Play;
import model.Player;
import model.Rank;
import model.Suit;
import model.eGame;

public class PlayTest {
	private static Play gameHoldem;
	private static Deck deckHoldem;
	private static Player playerHoldem;
	private static ArrayList<Card> cardsHoldem;
	// "p" denotes players cards, H denotes Omaha, c denotes community cards
	private static Card pH1, pH2, cH1, cH2, cH3, cH4, cH5;
	
	private static Play gameOmaha;
	private static Deck deckOmaha;
	private static Player playerOmaha;
	private static ArrayList<Card> cardsOmaha;
	private static Card pO1, pO2, pO3, pO4, cO1, cO2, cO3, cO4, cO5;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*
		 * All we have to do is rigg the deck to make sure that shit works.
		 * The Play class will take care of making the players hand and community cards.
		 */
		deckHoldem = new Deck();
		// Rigging for a straight
		pH1 = new Card(Rank.FIVE, Suit.CLUB);
		pH2 = new Card(Rank.TWO, Suit.SPADE);
		
		cH1 = new Card(Rank.TWO, Suit.CLUB);
		cH2 = new Card(Rank.SIX, Suit.DIAMOND);
		cH3 = new Card(Rank.SEVEN, Suit.HEART);
		cH4 = new Card(Rank.EIGHT, Suit.HEART);
		cH5 = new Card(Rank.NINE, Suit.DIAMOND);
		
		cardsHoldem.addAll(Arrays.asList(pH1, pH2, cH1, cH2, cH3, cH4, cH5));
		
		for (int i = 0; i < cardsHoldem.size(); i++){
			deckHoldem.getDeck().add(i, cardsHoldem.get(i));
		}
		
		
		deckOmaha = new Deck();
		// Rigging for a full house.
		pO1 = new Card(Rank.TWO, Suit.SPADE);
		pO2 = new Card(Rank.FOUR, Suit.CLUB);
		pO3 = new Card(Rank.FOUR, Suit.DIAMOND);
		pO4 = new Card(Rank.JACK, Suit.HEART);
		
		cO1 = new Card(Rank.TWO, Suit.CLUB);
		cO2 = new Card(Rank.TEN, Suit.DIAMOND);
		cO3 = new Card(Rank.EIGHT, Suit.SPADE);
		cO4 = new Card(Rank.FOUR, Suit.SPADE);
		cO5 = new Card(Rank.JACK, Suit.CLUB);
		
		cardsOmaha.addAll(Arrays.asList(pO1, pO2, pO3, pO4, cO1, cO2, cO3, cO4, cO5));
		for (int i = 0; i < cardsOmaha.size(); i++){
			deckOmaha.getDeck().add(i, cardsOmaha.get(i));
		}
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHoldem() {
		gameHoldem.setGameType(eGame.HOLDEM);
		gameHoldem.addPlayer(playerHoldem);
		gameHoldem.setDeck(deckHoldem);
		gameHoldem.play();
		
		// The player could have a pair or straight...should be straight
		assertEquals(gameHoldem.getWinners().get(0).getHand().getHandType(), HandType.STRAIGHT);
	}
	
	@Test
	public void testOmaha(){
		gameOmaha.setGameType(eGame.OMAHA);
		gameOmaha.addPlayer(playerOmaha);
		gameOmaha.setDeck(deckOmaha);
		gameOmaha.play();
		
		assertEquals(gameOmaha.getWinners().get(0).getHand().getHandType(), HandType.FULL_HOUSE);
	}

}
