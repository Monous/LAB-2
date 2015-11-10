package PokerPackage;

import java.util.ArrayList;
import java.util.Scanner;

import domain.PlayDomain;


/**
 * @author Moheem Ilyas
 * 
 * Created:				9/19/2015
 * Last Modified:		9/25/2015
 * Description:			This class serves to play an actual game of poker. The essential features of this class is to 
 * 						prompt the user for the deck size and the number of players. Hands will then be generated for each player,
 * 						printed to the console, and evaluated. The evaluation will print to the console.
 * Performance Issues:	(1) Error handling is not done for the user input.
 * 						(2) The DeckOutOfCardsException caught by the main class will only be caught once. If another error of this type occurs, program crashes.
 * 						(3) The statistics for the hand probability do not seem to match up. There has been no quantitative analysis as of yet, however.
 * Assignment:          Lab 2
 *
 */
public class Play extends PlayDomain {
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Player> winners = new ArrayList<Player>();
	private eGame gameType;
	private Deck deck;
	private ArrayList<Card> communityCards = new ArrayList<Card>();
	
	public Play(eGame gameType){
		this.gameType = gameType;		
	}
	
	public void addPlayer(Player p){
		this.players.add(p);
	}
	
	public void setPlayers(ArrayList<Player> players){
		this.players = players;
	}
	
	public void setGameType(eGame gameType, ArrayList<Player> players){
		this.players = players;
		this.gameType = gameType;
	}
	
	public eGame getEGame(){
		return this.gameType;
	}
	
	public void play(){
		switch (this.gameType){
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
	
	private void playFiveStud(){
		this.deck = new Deck(1, 0, 0);
		ArrayList<Hand> playerHands = new ArrayList<Hand>();
		for (Player p : this.players){
			p.setHand(this.deck);
		}
		
		ArrayList<Integer> positions = HandType.judgeHands(playerHands);
		for (Integer i : positions){
			winners.add(players.get(i));
		}
	}
	
	private void playFiveDraw(){
		
	}
	
	private void playFiveJoker(){
		
	}
	
	private void playFiveWild(){
		
	}
	
	private void playSevenDraw(){
		
	}
	
	private void playHoldEm(){
		
	}
	
	private void playOmaha(){
		
	}
}
