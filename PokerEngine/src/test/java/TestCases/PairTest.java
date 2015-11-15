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
import model.Hand;
import model.HandType;
import model.Rank;
import model.Suit;

public class PairTest {
	public static Card c10 = new Card(Rank.THREE, Suit.CLUB);
	public static Card c11 = new Card(Rank.ACE, Suit.CLUB);
	public static Card c12 = new Card(Rank.SEVEN, Suit.CLUB);
	public static Card c13 = new Card(Rank.ACE, Suit.SPADE);
	public static Card c14 = new Card(Rank.TWO, Suit.SPADE);
	public static Hand hand1 = new Hand(c10,c11,c12,c13,c14);
	
	public static Card c20 = new Card(Rank.FOUR, Suit.CLUB);
	public static Card c21 = new Card(Rank.FOUR, Suit.HEART);
	public static Card c22 = new Card(Rank.SIX, Suit.CLUB);
	public static Card c23 = new Card(Rank.EIGHT, Suit.CLUB);
	public static Card c24 = new Card(Rank.TEN, Suit.CLUB);
	public static Hand hand2 = new Hand(c20,c21,c22,c23,c24);
	
	public static Card c40 = new Card(Rank.FOUR, Suit.CLUB);
	public static Card c41 = new Card(Rank.NINE, Suit.HEART);
	public static Card c42 = new Card(Rank.SIX, Suit.CLUB);
	public static Card c43 = new Card(Rank.FIVE, Suit.CLUB);
	public static Card c44 = new Card(Rank.EIGHT, Suit.CLUB);
	public static Hand hand4 = new Hand(c40,c41,c42,c43,c44);
	
	public static Card c30 = new Card(Rank.SEVEN, Suit.SPADE);
	public static Card c31 = new Card(Rank.TWO, Suit.HEART);
	public static Card c32 = new Card(Rank.TWO, Suit.DIAMOND);
	public static Card c33 = new Card(Rank.EIGHT, Suit.HEART);
	public static Card c34 = new Card(Rank.TEN, Suit.HEART);
	public static Hand hand3 = new Hand(c30,c31,c32,c33,c34);
	
	public static ArrayList<Hand> hands = new ArrayList<>();
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		hands.addAll(Arrays.asList(hand1, hand2, hand4, hand3));
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
		System.out.println(HandType.judgeHands(hands));
		
	}

}
