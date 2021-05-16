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
	
	// Resets the a5 counter
	public void reset_count() {
		this.a5_counter = 0;
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
	
	public int advice(Hand player, Card card_dealer, Shoe shoe, Player p) {
		int res = 0;
		
		if(a5_counter >= 2) {
			if(curr_bet*2 >= max_bet)
				res = 2;
			else
				res = 1;
		}
		else if(a5_counter <= 1)
			res = -1;
				
		return res;
	}
	
	public void print_advice(int res) {
		switch(res) {
			case 2:
				System.out.println("ace-five	bet " + max_bet);
				break;
			case 1:
				System.out.println("ace-five	bet " + curr_bet*2);
				break;
			case -1:
			  	System.out.println("ace-five	bet " + min_bet);
			  	break;
		  	default:
		  		System.out.println("You shoudn't be here! (A5 advice Error)");
		}
	}
	
	public int make_advice(int res) {
		int value = 0;
		
		switch(res) {
			case 2:
				value = max_bet;
				break;
			case 1:
				value = curr_bet*2;
				break;
			case -1:
				value = min_bet;
				break;
			default:
				System.out.println("You shoudn't be here! (A5 Error)");
		}
		
		return value;
	}
	

}
