package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import PokerPackage.Card;
import PokerPackage.Hand;
import PokerPackage.HandType;
import PokerPackage.Rank;
import PokerPackage.Suit;

/**
 * 
 * @author Roy Cheng
 *
 */

public class HandTypeTest {
	private static Card s1 = new Card(Rank.ACE, Suit.SPADE);
	private static Card s2 = new Card(Rank.TWO, Suit.SPADE);
	private static Card s3 = new Card(Rank.THREE, Suit.SPADE);
	private static Card s4 = new Card(Rank.FOUR, Suit.SPADE);
	private static Card s5 = new Card(Rank.FIVE, Suit.SPADE);
	private static Card s6 = new Card(Rank.SIX, Suit.SPADE);
	private static Card s7 = new Card(Rank.SEVEN, Suit.SPADE);
	private static Card s8 = new Card(Rank.EIGHT, Suit.SPADE);
	private static Card s9 = new Card(Rank.NINE, Suit.SPADE);
	private static Card s10 = new Card(Rank.TEN, Suit.SPADE);
	private static Card s11 = new Card(Rank.JACK, Suit.SPADE);
	private static Card s12 = new Card(Rank.QUEEN, Suit.SPADE);
	private static Card s13 = new Card(Rank.KING, Suit.SPADE);
	private static Card d1 = new Card(Rank.ACE, Suit.DIAMOND);
	private static Card d2 = new Card(Rank.TWO, Suit.DIAMOND);
	private static Card d3 = new Card(Rank.THREE, Suit.DIAMOND);
	private static Card d4 = new Card(Rank.FOUR, Suit.DIAMOND);
	private static Card d5 = new Card(Rank.FIVE, Suit.DIAMOND);
	private static Card d6 = new Card(Rank.SIX, Suit.DIAMOND);
	private static Card d7 = new Card(Rank.SEVEN, Suit.DIAMOND);
	private static Card d8 = new Card(Rank.EIGHT, Suit.DIAMOND);
	private static Card d9 = new Card(Rank.NINE, Suit.DIAMOND);
	private static Card d10 = new Card(Rank.TEN, Suit.DIAMOND);
	private static Card d11 = new Card(Rank.JACK, Suit.DIAMOND);
	private static Card d12 = new Card(Rank.QUEEN, Suit.DIAMOND);
	private static Card d13 = new Card(Rank.KING, Suit.DIAMOND);
	private static Card c1 = new Card(Rank.ACE, Suit.CLUB);
	private static Card c2 = new Card(Rank.TWO, Suit.CLUB);
	private static Card c3 = new Card(Rank.THREE, Suit.CLUB);
	private static Card c4 = new Card(Rank.FOUR, Suit.CLUB);
	private static Card c5 = new Card(Rank.FIVE, Suit.CLUB);
	private static Card c6 = new Card(Rank.SIX, Suit.CLUB);
	private static Card c7 = new Card(Rank.SEVEN, Suit.CLUB);
	private static Card c8 = new Card(Rank.EIGHT, Suit.CLUB);
	private static Card c9 = new Card(Rank.NINE, Suit.CLUB);
	private static Card c10 = new Card(Rank.TEN, Suit.CLUB);
	private static Card c11 = new Card(Rank.JACK, Suit.CLUB);
	private static Card c12 = new Card(Rank.QUEEN, Suit.CLUB);
	private static Card c13 = new Card(Rank.KING, Suit.CLUB);
	private static Card h1 = new Card(Rank.ACE, Suit.HEART);
	private static Card h2 = new Card(Rank.TWO, Suit.HEART);
	private static Card h3 = new Card(Rank.THREE, Suit.HEART);
	private static Card h4 = new Card(Rank.FOUR, Suit.HEART);
	private static Card h5 = new Card(Rank.FIVE, Suit.HEART);
	private static Card h6 = new Card(Rank.SIX, Suit.HEART);
	private static Card h7 = new Card(Rank.SEVEN, Suit.HEART);
	private static Card h8 = new Card(Rank.EIGHT, Suit.HEART);
	private static Card h9 = new Card(Rank.NINE, Suit.HEART);
	private static Card h10 = new Card(Rank.TEN, Suit.HEART);
	private static Card h11 = new Card(Rank.JACK, Suit.HEART);
	private static Card h12 = new Card(Rank.QUEEN, Suit.HEART);
	private static Card h13 = new Card(Rank.KING, Suit.HEART);
	private static Card special = new Card(Rank.JOKER);
	private static Hand hand1 = new Hand(s1,c2,d7,c9,s11);
	private static Hand hand2 = new Hand(s1,c1,d7,c9,s11);
	private static Hand hand2a = new Hand(s1,c7,d7,c9,s11);
	private static Hand hand2b = new Hand(s1,c2,d9,c9,s11);
	private static Hand hand2c = new Hand(s1,c2,d7,c11,s11);
	private static Hand hand3 = new Hand(s1,c1,d7,c7,s11);
	private static Hand hand3a = new Hand(s1,c1,d7,c11,s11);
	private static Hand hand3b = new Hand(s1,c7,d7,c11,s11);
	private static Hand hand4 = new Hand(s1,c1,d1,c9,s11);
	private static Hand hand4a = new Hand(s1,h9,d9,c9,s11);
	private static Hand hand4b = new Hand(s1,c2,d11,c11,s11);
	private static Hand hand5 = new Hand(s1,c2,d3,c4,s5);
	private static Hand hand5a = new Hand(s1,c10,d11,c12,s13);
	private static Hand hand6 = new Hand(s1,s2,s7,s9,s11);
	private static Hand hand7 = new Hand(s1,c1,d1,c2,s2);
	private static Hand hand7a = new Hand(s1,c1,d2,c2,s2);
	private static Hand hand8 = new Hand(s1,c1,d1,h1,s2);
	private static Hand hand8a = new Hand(s1,c2,d2,h2,s2);
	private static Hand hand9 = new Hand(s1,c1,d1,h1,special);
	private static Hand hand10 = new Hand(s1,s2,s3,s4,s5);
	private static Hand hand11 = new Hand(s1,s10,s11,s12,s13);
	
	private static List<Hand> handCombos = new ArrayList<Hand>();

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
	public void test() {
		assertEquals(hand1.getHandType(), HandType.NO_PAIR);
		assertEquals(hand2.getHandType(), HandType.ONE_PAIR);
		assertEquals(hand2a.getHandType(), HandType.ONE_PAIR);
		assertEquals(hand2b.getHandType(), HandType.ONE_PAIR);
		assertEquals(hand2c.getHandType(), HandType.ONE_PAIR);
		assertEquals(hand3.getHandType(), HandType.TWO_PAIR);
		assertEquals(hand3a.getHandType(), HandType.TWO_PAIR);
		assertEquals(hand3b.getHandType(), HandType.TWO_PAIR);
		assertEquals(hand4.getHandType(), HandType.THREE_OF_A_KIND);
		assertEquals(hand4a.getHandType(), HandType.THREE_OF_A_KIND);
		assertEquals(hand4b.getHandType(), HandType.THREE_OF_A_KIND);
		assertEquals(hand5.getHandType(), HandType.STRAIGHT);
		assertEquals(hand5a.getHandType(), HandType.STRAIGHT);
		assertEquals(hand6.getHandType(), HandType.FLUSH);
		assertEquals(hand7.getHandType(), HandType.FULL_HOUSE);
		assertEquals(hand7a.getHandType(), HandType.FULL_HOUSE);
		assertEquals(hand8.getHandType(), HandType.FOUR_OF_A_KIND);
		assertEquals(hand8a.getHandType(), HandType.FOUR_OF_A_KIND);
		assertEquals(hand9.getHandType(), HandType.FIVE_OF_A_KIND);
		assertEquals(hand10.getHandType(), HandType.STRAIGHT_FLUSH);
		assertEquals(hand11.getHandType(), HandType.ROYAL_FLUSH);
	}

}