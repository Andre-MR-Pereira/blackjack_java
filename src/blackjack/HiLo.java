package blackjack;

public class HiLo implements Strategies {

	int running_count;
	int ndecks;
	int true_count;
	
	// Constructor
	public HiLo() {
		this.running_count = 0;
		this.true_count = 0;
	}
	
	// Updates the running count based on the card dealt
	public void update_counter(Card c) {
		if(c.getCardvalue() >= 2 && c.getCardvalue() <= 6) {
			running_count++;
		} else if(c.getCardvalue() >= 10 || c.getCardvalue() == 1) {
			running_count--;
		}
	}
	
	public void update_true(Shoe s) {
		this.true_count = (int) Math.round(running_count/s.decks_left());
	}
	
	public void advice(Hand player, Card card_dealer, Shoe shoe) {
		update_true(shoe);
		// Illoustrous18 & Fab4 implementation
	}

}
