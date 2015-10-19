package TestCases;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import PokerPackage.Card;
import PokerPackage.Deck;
import PokerPackage.DeckOutOfCardsException;
import PokerPackage.Hand;
import PokerPackage.Rank;
import PokerPackage.Suit;

public class DeckTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void test() throws DeckOutOfCardsException {
		Deck deck1 = new Deck(1, 2, 0);
		Hand Player1Hand = new Hand(deck1);
		Hand Player2Hand = new Hand(deck1);
		Hand Player3Hand = new Hand(deck1);
		Deck deck2 = new Deck(1, 2, 12);
		Hand Player4Hand = new Hand(deck2);
		Hand Player5Hand = new Hand(deck2);
		Hand Player6Hand = new Hand(deck2);
		deck1.printDeck();
		System.out.println("");
		System.out.println(Player1Hand);
		System.out.println(Player2Hand);
		System.out.println(Player3Hand);
		deck2.printDeck();
		System.out.println("");
		System.out.println(Player4Hand);
		System.out.println(Player5Hand);
		System.out.println(Player6Hand);
		
	}

}

