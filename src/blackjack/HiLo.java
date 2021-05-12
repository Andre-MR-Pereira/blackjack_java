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
		if(player.handTotal()==21){
			System.out.println("You should stand!");
		}
		
		if(card_dealer.getCardface().equals("A")) {
			if(player.handTotal()==10) {
				if(true_count>=4) {
					System.out.println("hi-lo	double");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else if(player.handTotal()==11) {
				if(true_count>=1) {
					System.out.println("hi-lo	double");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else if(player.handTotal()==15) {
				if(true_count>=1) {
					System.out.println("hi-lo	surrender");
				}else {
					System.out.println("hi-lo	basic");
				}
				return;
			}else {
				if(true_count>=3) {
					System.out.println("hi-lo	insurance");
				}else {
					System.out.println("hi-lo	basic");
				}
				return;
			}
		}
		
		if(card_dealer.getCardface().equals("2")) {
			if(player.handTotal()==9) {
				if(true_count>=1) {
					System.out.println("hi-lo	double");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else if(player.handTotal()==12) {
				if(true_count>=3) {
					System.out.println("hi-lo	stand");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else if(player.handTotal()==13) {
				if(true_count>=-1) {
					System.out.println("hi-lo	stand");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else {
				System.out.println("hi-lo	basic");
				return;
			}
		}
		
		if(card_dealer.getCardface().equals("3")) {
			if(player.handTotal()==12) {
				if(true_count>=2) {
					System.out.println("hi-lo	stand");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else if(player.handTotal()==13) {
				if(true_count>=-2) {
					System.out.println("hi-lo	stand");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else {
				System.out.println("hi-lo	basic");
				return;
			}
		}
		
		if(card_dealer.getCardface().equals("4")) {
			if(player.handTotal()==12) {
				if(true_count>=0) {
					System.out.println("hi-lo	stand");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else {
				System.out.println("hi-lo	basic");
				return;
			}
		}
		
		if(card_dealer.getCardface().equals("5")) {
			if(player.handTotal()==12) {
				if(true_count>=-2) {
					System.out.println("hi-lo	stand");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else if(player.handTotal()==20) {
				if(true_count>=5) {
					System.out.println("hi-lo	split");
				}else {
					System.out.println("hi-lo	stand");
				}
				return;
			}else {
				System.out.println("hi-lo	basic");
				return;
			}
		}
		
		if(card_dealer.getCardface().equals("6")) {
			if(player.handTotal()==12) {
				if(true_count>=-1) {
					System.out.println("hi-lo	stand");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else if(player.handTotal()==20) {
				if(true_count>=4) {
					System.out.println("hi-lo	split");
				}else {
					System.out.println("hi-lo	stand");
				}
				return;
			}else {
				System.out.println("hi-lo	basic");
				return;
			}
		}
		
		if(card_dealer.getCardface().equals("7")) {
			if(player.handTotal()==9) {
				if(true_count>=3) {
					System.out.println("hi-lo	double");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else {
				System.out.println("hi-lo	basic");
				return;
			}
		}
		
		if(card_dealer.getCardface().equals("9")) {
			if(player.handTotal()==15) {
				if(true_count>=2) {
					System.out.println("hi-lo	surrender");
				}else {
					System.out.println("hi-lo	basic");
				}
				return;
			}else if(player.handTotal()==16) {
				if(true_count>=5) {
					System.out.println("hi-lo	stand");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else {
				System.out.println("hi-lo	basic");
				return;
			}
		}
		
		if(card_dealer.handValue()==10) {
			if(player.handTotal()==10) {
				if(true_count>=4) {
					System.out.println("hi-lo	double");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else if(player.handTotal()==14) {
				if(true_count>=3) {
					System.out.println("hi-lo	surrender");
				}else {
					System.out.println("hi-lo	basic");
				}
				return;
			}else if(player.handTotal()==15) {
				if(true_count>=0 || true_count<=3) {
					System.out.println("hi-lo	surrender");
				}else if(true_count>=4) {
					System.out.println("hi-lo	stand");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else if(player.handTotal()==16) {
				if(true_count>=0) {
					System.out.println("hi-lo	stand");
				}else {
					System.out.println("hi-lo	hit");
				}
				return;
			}else {
				System.out.println("hi-lo	basic");
				return;
			}
		}
		
	}

}
