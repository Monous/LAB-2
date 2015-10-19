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
public class handleJokersTest {
	private static Card c10 = new Card(Rank.KING, Suit.SPADE);
	private static Card c11 = new Card(Rank.KING, Suit.SPADE);
	private static Card c12 = new Card(Rank.QUEEN, Suit.SPADE);
	private static Card c13 = new Card(Rank.JOKER);
	private static Card c14 = new Card(Rank.JOKER);
	private static Hand hand1 = new Hand(c10,c11,c12,c13,c14);

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
		
		System.out.println(hand1.getHandType());
		System.out.println(hand1.getHand());
		
		//System.out.println(hand1.getHandInCombos(2703) + " " + hand1.getHandInCombos(2703).getHandType());
		
	}

}
