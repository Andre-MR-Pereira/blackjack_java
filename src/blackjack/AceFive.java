package blackjack;

public class AceFive implements Strategies{

	int a5_counter;
	int min_bet;
	int max_bet;
	int curr_bet;
	
	// Constructor
	public AceFive(int min, int max) {
		this.a5_counter = 0;
		this.min_bet = min;
		this.max_bet = max;
		this.curr_bet = min;
	}
	
	// Updates the ace-five counter based on the card dealt
	public void update_counter(Card c) {
		if(c.getCardvalue() == 5) {
			a5_counter++;
		} else if(c.getCardvalue() == 1) {
			a5_counter--;
		}
	}
	
	public void update_bet(int b) {
		this.curr_bet = b;
	}
	
	public void advice(Hand player, Card card_dealer, Shoe shoe) {
		
		if(a5_counter >= 2) {
			if(curr_bet*2 >= max_bet)
				System.out.println("You should bet the maximum: " + max_bet);
			else 
				System.out.println("You should double your previous bet: " + curr_bet*2);
		}
		else if(a5_counter <= 1)
			System.out.println("You should bet the minimum: " + min_bet);
		else
			System.out.println("You shoudn't be here! (A5 advice Error)");
	}

}
