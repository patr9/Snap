package Snap;

import java.util.ArrayList;
import java.util.Collections;

class Card
{
	//Variables
	private String suit;
	private String rank;

	//Constructor
	public Card(String suit, String rank)
	{
		setSuit(suit);
		setRank(rank);
	}
	
	//Getter functions
	public String getSuit()
	{
		return suit;
	}
	
	public String getRank()
	{
		return rank;
	}
	
	//Setter functions
	public void setSuit(String suit)
	{
		this.suit = suit;
	}
	
	public void setRank(String rank)
	{
		this.rank = rank;
	}
	
	//Makes it easy to print the card values
	public String toString()
	{
		return (rank+" of "+suit);
	}
}



//Main Game
public class Snap
{
	public static void main(String[] args)
	{
		//Variables
		ArrayList<Card> cards = createDeck();
		ArrayList<Card> player1 = new ArrayList<Card>();
		ArrayList<Card> player2 = new ArrayList<Card>();
		int i = 0;
		
		//Splits the deck evenly between both players
		for(Card card: cards)
		{
			if(i < 26)
			{
				player1.add(card);
				i++;
			}
			else
			{
				player2.add(card);
			}
		}
		
		//Variables
		boolean end = false;
		i = 0;
		Card temp = player2.get(i);
		boolean check = false;
		
		//Main game, keeps comparing cards until match found or no more cards
		while (end == false)
		{
			//Compares first 2 cards as base
			System.out.println("Player 1: "+player1.get(i));
			System.out.println("Player 2: "+player2.get(i));
			check = checkCards(player1.get(i), player2.get(i));
			
			//If first two cards match, it ends the game there
			if (check == true)
			{
				System.out.println("Snap!");
				i = 2222;
				end = true;
			}
			i++;
			
			//While loop keeps comparing cards after first 2
			while (i < player2.size())
			{
				//If player1 matches previous player2 card it ends
				System.out.println("Player 1: "+player1.get(i));
				check = checkCards(player1.get(i), temp);
				if (check == true)
				{
					System.out.println("Snap!");
					i = 2222;
					end = true;
				}
				
				//Checks if player2 matches previous player1 card
				else
				{
					temp = player1.get(i);
					System.out.println("Player 2: "+player2.get(i));
					check = checkCards(player2.get(i), temp);
					
					//If true, it ends the game
					if (check == true)
					{
						System.out.println("Snap!");
						i = 2222;
						end = true;
					}
					
					//If not, it keeps the game running
					else
					{
						temp = player2.get(i);
						i++;
					}
				}
			}
		}
		//Prints draw message if no matches found
		if(end == false)
		{
			System.out.println("Game over! It's a draw!");
		}
		end = true;
	}
	
	//Compares 2 cards to check if they are the same
	public static boolean checkCards(Card a, Card b)
	{
		//Checks if same value
		if(a.getRank() == b.getRank())
		{
			return true;
		}
		
		//Checks if it's a face value (K,Q,J)
		else if(a.getRank()== "Jack" || a.getRank()== "Queen" || a.getRank()== "King")
		{
			if(b.getRank() == "Jack" || b.getRank() == "Queen" || b.getRank() == "King")
			{
				return true;
			}
		}
		
		return false;
	}
	
	//Creates a randomised deck
	public static ArrayList<Card> createDeck()
	{
		//Variables
		String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
		String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
		ArrayList<Card> cards = new ArrayList<Card>();
		
		//Creates a deck of 52 unique cards
		for(int i = 0; i < suits.length; i++)
		{
			for( int j = 0; j < ranks.length; j++)
			{
				cards.add(new Card(suits[i],ranks[j]));
			}
		}
			
		//Randomises the deck
		Collections.shuffle(cards);
		return cards;
	}
}