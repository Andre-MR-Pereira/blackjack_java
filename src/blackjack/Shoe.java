package blackjack;

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
		int i, j; // exchange indexes
		
		// Repeat random exchanges a lot of times
		for(int k = 0; k < 2913; k++) {
			i = (int) (52*ndecks*Math.random()); // Picks 2 random cards (indexes)
			j = (int) (52*ndecks*Math.random());
			
			// Swap the cards
			Card temp = shoe[i];
			shoe[i] = shoe[j];
			shoe[j] = temp;
		}
		
		currCard = 0;
	}
}
