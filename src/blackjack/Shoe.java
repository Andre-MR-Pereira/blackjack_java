package blackjack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Shoe {

	private Card[] shoe;
	private int ndecks;
	private int  currCard; // Extra variable to advance through the shoe
	private int percentage;
	
	// Constructor = Fresh Shoe
	public Shoe(int ndecks,int percentage) {
		this.ndecks = ndecks;
		
		// Initialize Array of cards
		shoe = new Card[52*ndecks];
		
		int i = 0;
		for (int j = 0; j < ndecks; j++)
			for(int suit = 1; suit < 5; suit++)
				for(int face = 1; face <= 13; face++)
					shoe[i++] = new Card(face, suit);
		
		shuffle();
		this.percentage=percentage;
	}
	
	// Method that returns the dealt card
	public Card deal() {
		if(currCard < 52*ndecks)
		{
			Card curr =  shoe[currCard++];
			if ((float)currCard/((float)52*ndecks)> percentage) shuffle();
			return(curr);
		}
		else
		{
			// Error
			System.out.println("Out of cards.");
			return(null);
		}
		
		
	}
	
	// Shuffle the shoe
	public void shuffle() {
		
		System.out.println("shuffling the shoe...");
		
		List<Card> bufferList = Arrays.asList(shoe);

		Collections.shuffle(bufferList);

		bufferList.toArray(this.shoe);
		
		
		
		currCard = 0;
	}
}
