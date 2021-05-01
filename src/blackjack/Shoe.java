package blackjack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Shoe {

	private Card[] shoe;
	private int ndecks;
	private int  currCard; // Extra variable to advance through the shoe
	
	// Constructor = Fresh Shoe
	public Shoe(int ndecks) {
		this.ndecks = ndecks;
		
		// Initialize Array of cards
		shoe = new Card[52*ndecks];
		
		int i = 0;
		for(int suit = 0; suit < 4*ndecks; suit++) // (Colocar naipes caso necessário)
			for(int face = 1; face <= 13; face++)
				shoe[i++] = new Card(face);
		
		shuffle();
	}
	
	// Method that returns the dealt card
	public Card deal() {
		
		if(currCard < 52*ndecks)
		{
			return(shoe[currCard++]);
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
		
		List<Card> bufferList = Arrays.asList(shoe);

		Collections.shuffle(bufferList);

		bufferList.toArray(this.shoe);
		
		currCard = 0;
	}
}
