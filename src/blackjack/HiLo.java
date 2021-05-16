package blackjack;

public class HiLo implements Strategies {

	int running_count;
	int ndecks;
	double true_count;
	int strat;
	
	// Constructor
	public HiLo() {
		this.running_count = 0;
		this.true_count = 0;
		strat = 0;
	}
	
	// Resets the hilo counter
	public void reset_count() {
		this.running_count = 0;
	}
	
	public void set_strat(int i) {
		this.strat = i;
	}
	
	public int check_strat() {
		return strat;
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
		this.true_count = running_count/s.decks_left();
	}
	
	
	/*
	 * res = 0;
	 * System.out.println("hi-lo	basic");
	 * 
	 * res = 1;
	 * System.out.println("hi-lo	hit");
	 * 
	 * res = 2;
	 * System.out.println("hi-lo	stand");
	 * 
	 * res = 3;
	 * System.out.println("hi-lo	double");
	 * 
	 * res = 4;
	 * System.out.println("hi-lo	surrender");
	 * 
	 * res = 5;
	 * System.out.println("hi-lo	insurance");
	 * 
	 * res = 6;
	 * System.out.println("hi-lo	split");
	 *  
	 */
	
	public void print_advice(int res) {
		switch(res) {
			case 1:
				System.out.println("hi-lo	hit");
				break;
			case 2:
				System.out.println("hi-lo	stand");
				break;
			case 3:
				System.out.println("hi-lo	double");
			  	break;
			case 4:
				System.out.println("hi-lo	surrender");
				break;
			case 5:
				System.out.println("hi-lo	insurance");
				break;
			case 6:
				System.out.println("hi-lo	split");
				break;
		  	default:
		  		System.out.println("hi-lo	basic");
		}
	}
	
	public char make_advice(int res) {
		switch(res) {
			case 1:
				return 'h';
			case 2:
				return 's';
			case 3:
				return '2';
			case 4:
				return 'u';
			case 5:
				return 'i';
			case 6:
				return 'p';
		  	default:
		  		return '0';
		}
	}
	
	public int advice(Hand player, Card card_dealer, Shoe shoe, Player p) {
		update_true(shoe);
		
		if(card_dealer.getCardface().equals("A")) {
			
			if(true_count >= 3 && player.handSize() == 2 && p.hands.size() == 1 && p.check_insurance() == 0) {
				return 5;
			}
			
			else if(player.handTotal() == 10) {
				if(true_count >= 4 && player.handSize() == 2)
					return 3;
				else
					return 1;
			}
			
			else if(player.handTotal() == 11) {
				if(true_count >= 1 && player.handSize() == 2)
					return 3;
				else
					return 1;
			}
			
			else if(player.handTotal() == 15) {
				if(true_count >= 1 && player.handSize() == 2)
					return 4;
				else
					return 0;
			}
			
			else
				return 0;
			
		}
		
		if(card_dealer.getCardface().equals("2")) {
			
			if(player.handTotal() == 9) {
				if(true_count >= 1 && player.handSize() == 2)
					return 3;
				else
					return 1;
			}
			
			else if(player.handTotal() == 12) {
				if(true_count >= 3)
					return 2;
				else
					return 1;
			}
			
			else if(player.handTotal() == 13) {
				if(true_count >= -1)
					return 2;
				else
					return 1;
			}
			
			else
				return 0;
			
		}
		
		if(card_dealer.getCardface().equals("3")) {
			
			if(player.handTotal() == 12) {
				if(true_count >= 2)
					return 2;
				else
					return 1;
			}
			
			else if(player.handTotal() == 13) {
				if(true_count >= -2)
					return 2;
				else
					return 1;
			}
			
			else
				return 0;
			
		}
		
		if(card_dealer.getCardface().equals("4")) {
			
			if(player.handTotal() == 12) {
				if(true_count >= 0)
					return 2;
				else
					return 1;
			}
			
			else
				return 0;
			
		}
		
		if(card_dealer.getCardface().equals("5")) {
			
			if(player.handTotal() == 12) {
				if(true_count >= -2)
					return 2;
				else
					return 1;
			}
			
			else if(player.handTotal() == 20) {
				if(true_count >= 5 && player.handSize() == 2 && p.hands.size() < 4)
					return 6;
				else
					return 2;
			}
			
			else
				return 0;
			
		}
		
		if(card_dealer.getCardface().equals("6")) {
			
			if(player.handTotal() == 12) {
				if(true_count >= -1)
					return 2;
				else
					return 1;
			}
			
			else if(player.handTotal() == 20) {
				if(true_count >= 4 && player.handSize() == 2 && p.hands.size() < 4)
					return 6;
				else
					return 2;
			}
			
			else
				return 0;

		}
		
		if(card_dealer.getCardface().equals("7")) {
			
			if(player.handTotal() == 9) {
				if(true_count >= 3 && player.handSize() == 2)
					return 3;
				else
					return 1;
			}
			
			else
				return 0;

		}
		
		if(card_dealer.getCardface().equals("9")) {
			
			if(player.handTotal() == 15) {
				if(true_count >= 2 && player.handSize() == 2)
					return 4;
				else
					return 0;
			}
			
			else if(player.handTotal() == 16) {
				if(true_count >= 5)
					return 2;
				else
					return 1;
			}
			
			else
				return 0;

		}
		
		if(card_dealer.handValue() == 10) {
			
			if(player.handTotal() == 10) {
				if(true_count >= 4 && player.handSize() == 2)
					return 3;
				else
					return 1;
			}
			
			else if(player.handTotal() == 14) {
				if(true_count >= 3 && player.handSize() == 2)
					return 4;
				else
					return 0;
			}
			
			else if(player.handTotal() == 15) {
				if((true_count >= 0 || true_count <= 3) && player.handSize() == 2)
					return 4;
				else if(true_count >= 4)
					return 2;
				else
					return 1;
			}
			
			else if(player.handTotal() == 16) {
				if(true_count >= 0)
					return 2;
				else
					return 1;
			}
			
		}
		
		return 0;
	}

}
