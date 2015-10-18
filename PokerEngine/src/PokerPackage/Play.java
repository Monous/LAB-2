package PokerPackage;

import java.util.ArrayList;
import java.util.Scanner;

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
public class Play {

	public static void main(String[] args) throws DeckOutOfCardsException {
		try {
			playPoker();
			/*
			 * The exception below will only be caught once.
			 */
		} catch (DeckOutOfCardsException e) {
			Scanner input = new Scanner(System.in);
			System.out.println("The deck is out of cards, would you like to add more?");
			String response = input.nextLine().toString();
			if (response.equals("yes") || response.equals("Yes")) {
				playPoker();
			}else {
				input.close();
				return;
			}
			
			input.close();
		}
	}

	/*
	 * Method to create a deck and make hands out of that deck.
	 */
	public static void playPoker() throws DeckOutOfCardsException {
		Scanner input = new Scanner(System.in);

		System.out.println("How many decks do you want to have?");
		// Error handling needs to be implemented in case the user enters a number 0 or below.
		int numOfDecks = input.nextInt();
		System.out.println("How many Jokers do you want?");
		int numOfJokers = input.nextInt();
		Deck deck = new Deck(numOfDecks, numOfJokers, 0);
		
		System.out.println("How many players are there?");
		int numOfPlayers = input.nextInt();
		ArrayList<Hand> playerHands = new ArrayList<Hand>();
		for (int i = 0; i < numOfPlayers; i++){
			playerHands.add( new Hand(deck));
		}
		
		input.close();
		
		printHands(playerHands);
		printWinner(playerHands);
	}
	
	/*
	 * Method to print the hands formatted nice and neat.
	 */
	public static void printHands(ArrayList<Hand> hands){
		for (int i = 0; i < hands.size(); i++) {
			System.out.println("Player " + (i + 1));
			System.out.println(hands.get(i));
			System.out.println(hands.get(i).getHandType());
			System.out.println("");
		}
	}
	
	/*
	 * Method that Determines the winner and prints the winner. 
	 */
	public static void printWinner(ArrayList<Hand> hands){
		ArrayList<Integer> posOfBestHands = HandType.judgeHands(hands);
		if (posOfBestHands.size() == 1){
			System.out.println("Player " + (posOfBestHands.get(0) + 1) + " wins!");
		}
	}

}
